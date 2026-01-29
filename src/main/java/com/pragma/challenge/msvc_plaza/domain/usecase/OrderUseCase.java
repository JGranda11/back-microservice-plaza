package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.api.OrderServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.CustomerAlreadyHasProcessingOrderException;
import com.pragma.challenge.msvc_plaza.domain.exception.DishDoesNotBelongToOrderRestaurantException;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.model.order.OrderDish;
import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.EmployeePersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.OrderPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.domain.util.DomainConstants;
import com.pragma.challenge.msvc_plaza.domain.util.GenerationPIN;
import com.pragma.challenge.msvc_plaza.domain.util.TokenHolder;
import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;

import java.util.Objects;

public class OrderUseCase implements OrderServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;
    private final DishPersistencePort dishPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;

    public OrderUseCase(OrderPersistencePort orderPersistencePort,
                        RestaurantPersistencePort restaurantPersistencePort,
                        AuthorizationSecurityPort authorizationSecurityPort,
                        DishPersistencePort dishPersistencePort,
                        EmployeePersistencePort employeePersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
        this.dishPersistencePort = dishPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
    }

    @Override
    public Order createOrder(Order order) {
        AuthorizedUser user = authorizationSecurityPort.authorize(
                TokenHolder.getToken().substring(DomainConstants.TOKEN_PREFIX.length()));
        validateCustomerCanAddOrder(order, user);
        order.setState(OrderState.WAITING);
        order.setSecurityPin(GenerationPIN.generateRandomSecurityPin());
        order.setCustomerId(user.getId());
        return orderPersistencePort.saveOrder(order);
    }

    private void validateCustomerCanAddOrder(Order order, AuthorizedUser user) {
        // Current user has not another processing order
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
                orderDish.getDish().getId().toString()
        );
        if(!Objects.equals(dish.getRestaurant().getId(), restaurant.getId()))
            throw new DishDoesNotBelongToOrderRestaurantException(dish.getName(), restaurant.getName());
    }
}
