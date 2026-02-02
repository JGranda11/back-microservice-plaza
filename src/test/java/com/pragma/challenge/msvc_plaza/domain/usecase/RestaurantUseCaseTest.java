package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.exception.EntityAlreadyExistsException;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_plaza.domain.exception.OwnerHasNotRestaurantException;
import com.pragma.challenge.msvc_plaza.domain.exception.UserRoleMustBeOwnerException;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.EmployeePersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.domain.util.DomainConstants;
import com.pragma.challenge.msvc_plaza.domain.util.TokenHolder;
import com.pragma.challenge.msvc_plaza.domain.util.enums.RoleName;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestaurantUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private AuthorizationSecurityPort authorizationSecurityPort;

    @Mock
    private EmployeePersistencePort employeePersistencePort;

    @Mock
    private DishPersistencePort dishPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private static final Long RESTAURANT_ID = 5L;
    private static final String RESTAURANT_NIT = "987654321";
    private static final Long RESTAURANT_OWNER_ID = 3L;
    private static final String RESTAURANT_NAME = "Mock Restaurant";
    private static final String RESTAURANT_ADDRESS = "123 Fake Street";
    private static final String RESTAURANT_PHONE = "555-1234";
    private static final String RESTAURANT_LOGO_URL = "http://example.com/logo.png";

    private Restaurant mockRestaurant;
    private Restaurant expectedRestaurant;

    public static final String TOKEN = "Bearer any-token-buh";
    private static final String CATEGORY_MAIN_COURSE = "Main Course";
    private static final String CATEGORY_DESSERTS = "Desserts";

    private static final AuthorizedUser mockEmployee = AuthorizedUser.builder()
            .role(RoleName.EMPLOYEE)
            .token("user-authorization-token")
            .id("employee-id")
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockRestaurant = Restaurant.builder()
                .nit(RESTAURANT_NIT)
                .ownerId(RESTAURANT_OWNER_ID)
                .name(RESTAURANT_NAME)
                .address(RESTAURANT_ADDRESS)
                .phone(RESTAURANT_PHONE)
                .logoUrl(RESTAURANT_LOGO_URL)
                .build();

        expectedRestaurant = Restaurant.builder()
                .id(RESTAURANT_ID)
                .nit(RESTAURANT_NIT)
                .ownerId(RESTAURANT_OWNER_ID)
                .name(RESTAURANT_NAME)
                .address(RESTAURANT_ADDRESS)
                .phone(RESTAURANT_PHONE)
                .logoUrl(RESTAURANT_LOGO_URL)
                .build();

        TokenHolder.setToken("Bearer fake-token");
    }

    @Test
    void shouldCreateRestaurantWhenOwnerIsValidAndNitDoesNotExist() {
        // Arrange
        when(userPersistencePort.isOwner(RESTAURANT_OWNER_ID)).thenReturn(true);
        when(restaurantPersistencePort.findByNit(RESTAURANT_NIT)).thenReturn(null);
        when(restaurantPersistencePort.saveRestaurant(any(Restaurant.class))).thenReturn(expectedRestaurant);

        // Act
        Restaurant result = restaurantUseCase.createRestaurant(mockRestaurant);

        // Assert
        verify(userPersistencePort).isOwner(RESTAURANT_OWNER_ID);
        verify(restaurantPersistencePort).findByNit(RESTAURANT_NIT);
        verify(restaurantPersistencePort).saveRestaurant(mockRestaurant);

        assertNotNull(result);
        assertEquals(RESTAURANT_ID, result.getId());
        assertEquals(RESTAURANT_NAME, result.getName());
        assertEquals(RESTAURANT_LOGO_URL, result.getLogoUrl());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNotOwner() {
        // Arrange
        when(userPersistencePort.isOwner(RESTAURANT_OWNER_ID)).thenReturn(false);

        // Act y Assert
        assertThrows(UserRoleMustBeOwnerException.class,
                () -> restaurantUseCase.createRestaurant(mockRestaurant));

        verify(userPersistencePort).isOwner(RESTAURANT_OWNER_ID);
        verify(restaurantPersistencePort, never()).findByNit(any());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantAlreadyExists() {
        // Arrange
        when(userPersistencePort.isOwner(RESTAURANT_OWNER_ID)).thenReturn(true);
        when(restaurantPersistencePort.findByNit(RESTAURANT_NIT)).thenReturn(expectedRestaurant);

        // Act y Assert
        assertThrows(EntityAlreadyExistsException.class,
                () -> restaurantUseCase.createRestaurant(mockRestaurant));

        verify(userPersistencePort).isOwner(RESTAURANT_OWNER_ID);
        verify(restaurantPersistencePort).findByNit(RESTAURANT_NIT);
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void registerEmployee_Success() {
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(authorizationSecurityPort.authorize(any())).thenReturn(
                AuthorizedUser.builder().id(String.valueOf(RESTAURANT_OWNER_ID)).role(RoleName.OWNER).build()
        );
        when(employeePersistencePort.saveEmployee(any())).thenReturn(
                Employee.builder().restaurant(mockRestaurant).build()
        );

        Employee employee = Employee.builder().restaurant(mockRestaurant).build();

        Employee registeredEmployee = restaurantUseCase.registerEmployee(employee);

        // Assert
        verify(restaurantPersistencePort).findById(any());
        verify(authorizationSecurityPort).authorize(any());
        verify(employeePersistencePort).saveEmployee(any());
        assertNotNull(registeredEmployee);
    }

    @Test
    void registerEmployee_RestaurantNotFound() {
        when(restaurantPersistencePort.findById(any())).thenReturn(null);

        Employee employee = Employee.builder().restaurant(mockRestaurant).build();

        assertThrows(EntityNotFoundException.class, () -> restaurantUseCase.registerEmployee(employee));
        verify(employeePersistencePort, never()).saveEmployee(any());
    }

    @Test
    void findPage_ShouldReturnDomainPageOfRestaurants() {
        PaginationData paginationData = PaginationData.builder().build();
        DomainPage<Restaurant> expectedPage = DomainPage.<Restaurant>builder().build();

        when(restaurantPersistencePort.findAll(any(PaginationData.class))).thenReturn(expectedPage);

        DomainPage<Restaurant> result = restaurantUseCase.findPage(paginationData);

        verify(restaurantPersistencePort).findAll(paginationData);
        assertEquals(expectedPage, result);
        assertEquals(DomainConstants.NAME_FIELD, paginationData.getColumn());
    }

    @Test
    void findDishesOfRestaurant_ShouldReturnDomainPageOfDishes() {
        PaginationData paginationData = PaginationData.builder().build();
        DomainPage<Dish> expectedPage = DomainPage.<Dish>builder().build();

        when(dishPersistencePort.findAll(any(), any())).thenReturn(expectedPage);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);

        DomainPage<Dish> result = restaurantUseCase.findDishesOfRestaurant(RESTAURANT_ID, CATEGORY_MAIN_COURSE, paginationData);

        verify(dishPersistencePort).findAll(any(), eq(paginationData));
        assertEquals(expectedPage, result);
    }
    @Test
    void findCurrentOwnerRestaurant_Success() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockEmployee);
        when(restaurantPersistencePort.findByOwnerId(any())).thenReturn(mockRestaurant);

        Restaurant result = restaurantUseCase.findCurrentOwnerRestaurant();

        assertNotNull(result);
        verify(restaurantPersistencePort).findByOwnerId(any());
    }

    @Test
    void findCurrentOwnerRestaurant_OwnerHasNoRestaurant() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockEmployee);
        when(restaurantPersistencePort.findByOwnerId(any())).thenReturn(null);

        assertThrows(OwnerHasNotRestaurantException.class, () -> restaurantUseCase.findCurrentOwnerRestaurant());
    }
}
