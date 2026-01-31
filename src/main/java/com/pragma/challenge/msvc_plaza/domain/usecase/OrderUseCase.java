package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.api.OrderServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.*;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.User;
import com.pragma.challenge.msvc_plaza.domain.model.messaging.Notification;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.model.order.OrderDish;
import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.domain.spi.*;
import com.pragma.challenge.msvc_plaza.domain.spi.messaging.NotificationSenderPort;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.domain.util.DomainConstants;
import com.pragma.challenge.msvc_plaza.domain.util.GenerationPIN;
import com.pragma.challenge.msvc_plaza.domain.util.TokenHolder;
import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import com.pragma.challenge.msvc_plaza.domain.util.enums.RoleName;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;

import java.util.Objects;

public class OrderUseCase implements OrderServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;
    private final DishPersistencePort dishPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final NotificationSenderPort notificationSenderPort;

    public OrderUseCase(OrderPersistencePort orderPersistencePort,
                        RestaurantPersistencePort restaurantPersistencePort,
                        AuthorizationSecurityPort authorizationSecurityPort,
                        DishPersistencePort dishPersistencePort,
                        EmployeePersistencePort employeePersistencePort,
                        UserPersistencePort userPersistencePort,
                        NotificationSenderPort notificationSenderPort) {

        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
        this.dishPersistencePort = dishPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.notificationSenderPort = notificationSenderPort;
    }

    @Override
    public Order createOrder(Order order) {
        AuthorizedUser user = getCurrentUser();
        validateCustomerCanAddOrder(order, user);
        order.setState(OrderState.WAITING);
        order.setSecurityPin(GenerationPIN.generateRandomSecurityPin());
        order.setCustomerId(user.getId());
        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public DomainPage<Order> findOrders(OrderFilter filter, PaginationData paginationData) {
        AuthorizedUser user = getCurrentUser();
        if(user.getRole() != RoleName.EMPLOYEE)
            throw new NotAuthorizedException();
        Employee employee = employeePersistencePort.findById(user.getId());
        filter.setRestaurantId(String.valueOf(employee.getRestaurant().getId()));
        return orderPersistencePort.findOrders(filter, paginationData);
    }

    @Override
    public Order setAssignedEmployee(Long id) {
        Order order = orderPersistencePort.findById(id);
        if(order == null) throw new EntityNotFoundException(Order.class.getSimpleName(), id.toString());

        if(order.getAssignedEmployee() != null) throw new OrderIsAlreadyAssignedException();

        AuthorizedUser user = getCurrentUser();
        if (user.getRole() != RoleName.EMPLOYEE) throw new NotAuthorizedException();

        Employee employee = employeePersistencePort.findById(user.getId());
        if (!Objects.equals(employee.getRestaurant().getId(), order.getRestaurant().getId()))
            throw new NotAuthorizedException();
        order.setAssignedEmployee(employee);
        order.setState(OrderState.PREPARING);
        return orderPersistencePort.updateOrder(order);
    }

    @Override
    public Order setOrderAsDone(Long id) {
        AuthorizedUser user = getCurrentUser();
        if (user.getRole() != RoleName.EMPLOYEE) throw new NotAuthorizedException();
        Order order = orderPersistencePort.findById(id);
        if (order == null) throw new EntityNotFoundException(Order.class.getSimpleName(), id.toString());

        if (!Objects.equals(order.getAssignedEmployee().getId(), user.getId())) throw new OrderIsAlreadyAssignedException();
        if (order.getState() != OrderState.PREPARING) throw new OrderIsNotInPreparationStateException();

        try {
            notificationSenderPort.sendNotification(
                    buildNotification(order.getCustomerId(), order.getSecurityPin())
            );
        } catch (Exception e){
            throw new NotificationWasNotSentException();
        }

        order.setState(OrderState.DONE);
        return orderPersistencePort.updateOrder(order);

    }

    private void validateCustomerCanAddOrder(Order order, AuthorizedUser user) {
        OrderFilter filter = OrderFilter.builder()
                .customerId(user.getId())
                .states(DomainConstants.PROCESS_STATES)
                .build();
        if(!orderPersistencePort.findFilteredOrders(filter).isEmpty())
            throw new CustomerAlreadyHasProcessingOrderException();

        // Restaurant Exists
        Restaurant restaurant = restaurantPersistencePort.findById(order.getRestaurant().getId());
        if (restaurant == null)
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(),
                                            String.valueOf(order.getRestaurant().getId()));

        // Dishes exist
        order.getDishes().forEach(dish -> validateOrderDish(dish, restaurant));
    }

    private void validateOrderDish(OrderDish orderDish, Restaurant restaurant){
        Dish dish =  dishPersistencePort.findById(orderDish.getDish().getId());
        if(dish == null) throw new EntityNotFoundException(
                Dish.class.getSimpleName(),
                orderDish.getDish().getId().toString());
        if(!Objects.equals(dish.getRestaurant().getId(), restaurant.getId()))
            throw new DishDoesNotBelongToOrderRestaurantException(dish.getName(), restaurant.getName());
    }

    private AuthorizedUser getCurrentUser(){
        return authorizationSecurityPort.authorize(
                TokenHolder.getToken().substring(DomainConstants.TOKEN_PREFIX.length())
        );
    }

    private Notification buildNotification(String customerId, String code) {
        User user = userPersistencePort.getUser(customerId);
        return Notification.builder()
                .receiver(user.getPhone())
                .message(String.format(
                        DomainConstants.NOTIFICATION_MESSAGE_TEMPLATE,
                        user.getName(), user.getLastname(), code))
                .build();
    }
}
