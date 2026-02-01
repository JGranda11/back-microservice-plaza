package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.exception.CustomerAlreadyHasProcessingOrderException;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_plaza.domain.exception.NotAuthorizedException;
import com.pragma.challenge.msvc_plaza.domain.exception.OrderIsBeingPreparedException;
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
import com.pragma.challenge.msvc_plaza.domain.util.TokenHolder;
import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import com.pragma.challenge.msvc_plaza.domain.util.enums.RoleName;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OrderUseCaseTest {

    private static final String USER_ID = "user-id";
    private static final RoleName USER_ROLE = RoleName.CUSTOMER;
    private static final String USER_TOKEN = "user-authorization-token";
    private static final Long ORDER_ID = 1L;
    private static final Long RESTAURANT_ID = 3L;
    private static final String RESTAURANT_NAME = "Mock Restaurant";
    private static final Long DISH_ID = 101L;
    private static final String DISH_NAME = "Mock Dish";

    private static final String EMPLOYEE_ID = "5";
    private static final String CUSTOMER_ID = "CUSTOMER_456";
    private static final String SECURITY_PIN = "1234";
    private static final String CUSTOMER_PHONE = "9876543210";
    private static final String CUSTOMER_NAME = "John";
    private static final String CUSTOMER_LASTNAME = "Doe";

    private static final AuthorizedUser mockUser = AuthorizedUser.builder()
            .role(USER_ROLE)
            .token(USER_TOKEN)
            .id(USER_ID)
            .build();

    @Mock
    private OrderPersistencePort orderPersistencePort;
    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;
    @Mock
    private DishPersistencePort dishPersistencePort;
    @Mock
    private AuthorizationSecurityPort authorizationSecurityPort;
    @Mock
    private EmployeePersistencePort employeePersistencePort;
    @Mock
    private NotificationSenderPort notificationSenderPort;
    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;


    private static final Restaurant mockRestaurant = Restaurant.builder()
            .id(RESTAURANT_ID)
            .name(RESTAURANT_NAME)
            .build();

    private static final Dish mockDish = Dish.builder()
            .id(DISH_ID)
            .name(DISH_NAME)
            .restaurant(mockRestaurant)
            .build();

    private static final Order mockOrder = Order.builder()
            .id(ORDER_ID)
            .customerId(USER_ID)
            .restaurant(mockRestaurant)
            .dishes(List.of(
                    OrderDish.builder()
                            .id(DISH_ID)
                            .dish(mockDish)
                            .quantity(1)
                            .build()))
            .state(OrderState.WAITING)
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TokenHolder.setToken(USER_TOKEN);
    }

    @Test
    void createOrder_Success() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(orderPersistencePort.findFilteredOrders(any())).thenReturn(Collections.emptyList());
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(dishPersistencePort.findById(any())).thenReturn(mockDish);
        when(orderPersistencePort.saveOrder(any())).thenReturn(mockOrder);

        Order createdOrder = orderUseCase.createOrder(mockOrder);

        verify(orderPersistencePort).saveOrder(any());
        assertNotNull(createdOrder);
    }

    @Test
    void createOrder_CustomerHasProcessingOrder() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(orderPersistencePort.findFilteredOrders(any())).thenReturn(List.of(mockOrder));

        assertThrows(CustomerAlreadyHasProcessingOrderException.class, () -> orderUseCase.createOrder(mockOrder));
    }

    @Test
    void createOrder_DishNotFound() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(orderPersistencePort.findFilteredOrders(any())).thenReturn(Collections.emptyList());
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(dishPersistencePort.findById(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> orderUseCase.createOrder(mockOrder));
    }

    @Test
    void findOrders_Success() {
        AuthorizedUser mockEmployee = AuthorizedUser.builder()
                .role(RoleName.EMPLOYEE)
                .id(USER_ID)
                .token(USER_TOKEN)
                .build();

        Employee mockEmployeeEntity = Employee.builder()
                .id(3L)
                .restaurant(mockRestaurant)
                .build();

        OrderFilter filter = OrderFilter.builder().build();
        PaginationData paginationData =  PaginationData.builder().page(0).pageSize(10).build();
        DomainPage<Order> mockOrdersPage = DomainPage.<Order>builder()
                .page(1)
                .content(List.of(mockOrder))
                .totalCount(1L)
                .build();

        when(authorizationSecurityPort.authorize(any())).thenReturn(mockEmployee);
        when(employeePersistencePort.findById(USER_ID)).thenReturn(mockEmployeeEntity);
        when(orderPersistencePort.findOrders(any(), any())).thenReturn(mockOrdersPage);

        DomainPage<Order> result = orderUseCase.findOrders(filter, paginationData);

        assertNotNull(result);
        assertEquals(1L, result.getTotalCount());
        verify(orderPersistencePort).findOrders(any(), any());
    }

    @Test
    void findOrders_NotAuthorized() {
        AuthorizedUser mockCustomer = AuthorizedUser.builder()
                .role(RoleName.CUSTOMER)
                .id(USER_ID)
                .token(USER_TOKEN)
                .build();

        when(authorizationSecurityPort.authorize(any())).thenReturn(mockCustomer);
        OrderFilter filter = OrderFilter.builder().build();
        PaginationData paginationData =  PaginationData.builder().page(0).pageSize(10).build();

        assertThrows(NotAuthorizedException.class, () ->
                orderUseCase.findOrders(filter, paginationData));
    }

    @Test
    void shouldThrowNotAuthorizedException_WhenUserIsNotEmployee() {
        // Given
        AuthorizedUser unauthorizedUser = AuthorizedUser.builder()
                .id("USER_123")
                .role(RoleName.CUSTOMER)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(unauthorizedUser);

        // When & Then
        assertThrows(NotAuthorizedException.class, () -> orderUseCase.setOrderAsDone(1L));
        verifyNoInteractions(orderPersistencePort);
    }

    @Test
    void shouldSetOrderAsDoneSuccessfully() {
        AuthorizedUser employee = AuthorizedUser.builder()
                .id(null)
                .role(RoleName.EMPLOYEE)
                .build();

        Employee assignedEmployee = Employee.builder()
                .id(null)
                .build();

        Order order = Order.builder()
                .id(1L)
                .assignedEmployee(assignedEmployee)
                .state(OrderState.PREPARING)
                .customerId(CUSTOMER_ID)
                .securityPin(SECURITY_PIN)
                .build();

        User customer = User.builder()
                .name(CUSTOMER_NAME)
                .lastname(CUSTOMER_LASTNAME)
                .phone(CUSTOMER_PHONE)
                .build();

        // Mocks de los puertos
        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(order);
        when(userPersistencePort.getUser(CUSTOMER_ID)).thenReturn(customer);
        when(orderPersistencePort.updateOrder(any(Order.class))).thenReturn(order);

        // When
        Order result = orderUseCase.setOrderAsDone(1L);

        // Then
        assertEquals(OrderState.DONE, result.getState());
        assertEquals(OrderState.DONE, order.getState());
        verify(notificationSenderPort, times(1)).sendNotification(any(Notification.class));
        verify(orderPersistencePort, times(1)).updateOrder(any(Order.class));
    }

    @Test
    void orderDone_shouldThrowNotAuthorizedException_WhenUserIsNotEmployee() {
        // Given
        AuthorizedUser unauthorizedUser = AuthorizedUser.builder()
                .id("USER_123")
                .role(RoleName.CUSTOMER)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(unauthorizedUser);

        // When & Then
        assertThrows(NotAuthorizedException.class, () -> orderUseCase.setOrderAsDone(1L));
        verifyNoInteractions(orderPersistencePort);
    }

    @Test
    void orderDone_shouldThrowEntityNotFoundException_WhenOrderNotFound() {
        // Given
        AuthorizedUser employee = AuthorizedUser.builder()
                .id(EMPLOYEE_ID)
                .role(RoleName.EMPLOYEE)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(null);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> orderUseCase.setOrderAsDone(1L));
    }

    @Test
    void shouldSetOrderAsDeliveredSuccessfully() {
        AuthorizedUser employee = AuthorizedUser.builder()
                .id(null)
                .role(RoleName.EMPLOYEE)
                .build();

        Order order = Order.builder()
                .id(1L)
                .assignedEmployee(Employee.builder().id(null).build())
                .state(OrderState.DONE)
                .securityPin(SECURITY_PIN)
                .build();

        // Mocks de puertos necesarios
        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(order);
        when(orderPersistencePort.updateOrder(any(Order.class))).thenReturn(order);

        // When

        Order result = orderUseCase.setOrderAsDelivered(1L, SECURITY_PIN);

        // Then
        assertEquals(OrderState.DELIVERED, result.getState());
        verify(orderPersistencePort, times(1)).updateOrder(any(Order.class));

        // Ve
        assertDoesNotThrow(() -> {});
    }

    @Test
    void shouldCancelOrderSuccessfully() {
        String commonCustomerId = "CUSTOMER_123";

        AuthorizedUser customer = AuthorizedUser.builder()
                .id(commonCustomerId)
                .role(RoleName.CUSTOMER)
                .build();

        Order order = Order.builder()
                .id(ORDER_ID)
                .customerId(commonCustomerId)
                .state(OrderState.WAITING)
                .build();

        // Mocks
        when(authorizationSecurityPort.authorize(anyString())).thenReturn(customer);
        when(orderPersistencePort.findById(ORDER_ID)).thenReturn(order);
        when(orderPersistencePort.updateOrder(any(Order.class))).thenReturn(order);

        // When
        Order result = orderUseCase.setOrderAsCanceled(ORDER_ID);

        // Then
        assertEquals(OrderState.CANCELED, result.getState());
        verify(orderPersistencePort, times(1)).updateOrder(any(Order.class));
    }

    @Test
    void orderCanceled_shouldThrowNotAuthorizedException_WhenUserIsNotCustomer() {
        AuthorizedUser unauthorizedUser = AuthorizedUser.builder()
                .id("EMPLOYEE_123")
                .role(RoleName.EMPLOYEE)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(unauthorizedUser);

        assertThrows(NotAuthorizedException.class, () -> orderUseCase.setOrderAsCanceled(ORDER_ID));
        verifyNoInteractions(orderPersistencePort);
    }

    @Test
    void orderCanceled_shouldThrowEntityNotFoundException_WhenOrderNotFound() {
        AuthorizedUser customer = AuthorizedUser.builder()
                .id(CUSTOMER_ID)
                .role(RoleName.CUSTOMER)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(customer);
        when(orderPersistencePort.findById(ORDER_ID)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> orderUseCase.setOrderAsCanceled(ORDER_ID));
    }

    @Test
    void orderCanceled_shouldThrowOrderIsBeingPreparedException_WhenOrderIsNotWaiting() {
        AuthorizedUser customer = AuthorizedUser.builder()
                .id(CUSTOMER_ID)
                .role(RoleName.CUSTOMER)
                .build();

        Order order = Order.builder()
                .id(ORDER_ID)
                .customerId(CUSTOMER_ID)
                .state(OrderState.PREPARING) // Not WAITING
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(customer);
        when(orderPersistencePort.findById(ORDER_ID)).thenReturn(order);

        assertThrows(OrderIsBeingPreparedException.class, () -> orderUseCase.setOrderAsCanceled(ORDER_ID));
    }
}