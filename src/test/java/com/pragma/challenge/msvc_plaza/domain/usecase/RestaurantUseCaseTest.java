package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.exception.EntityAlreadyExistsException;
import com.pragma.challenge.msvc_plaza.domain.exception.UserRoleMustBeOwnerException;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
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

}
