package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityAlreadyExistsException;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_plaza.domain.exception.RestaurantDoesNotBelongToUserException;
import com.pragma.challenge.msvc_plaza.domain.exception.UserRoleMustBeOwnerException;
import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.domain.spi.EmployeePersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.domain.util.DomainConstants;
import com.pragma.challenge.msvc_plaza.domain.util.TokenHolder;

import java.util.Objects;

public class RestaurantUseCase implements RestaurantServicePort {
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;

    public RestaurantUseCase(RestaurantPersistencePort restaurantPersistencePort,
                             UserPersistencePort userPersistencePort,
                             EmployeePersistencePort employeePersistencePort,
                             AuthorizationSecurityPort authorizationSecurityPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        validateRestaurant(restaurant);
        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        validateRegisterEmployee(employee);
        return employeePersistencePort.saveEmployee(employee);
    }

    private void validateRestaurant(Restaurant restaurant){
        if (!userPersistencePort.isOwner(restaurant.getOwnerId())) {
            throw new UserRoleMustBeOwnerException();
        }
        if (restaurantPersistencePort.findByNit(restaurant.getNit()) != null) {
            throw new EntityAlreadyExistsException(Restaurant.class.getSimpleName(),
                    restaurant.getNit());
        }
    }

    private void validateRegisterEmployee(Employee employee){
        Restaurant restaurant = restaurantPersistencePort.findById(employee.getRestaurant().getId());

        if(restaurant == null){
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(),
                    String.valueOf(employee.getRestaurant().getId()));
        }

        AuthorizedUser user = authorizationSecurityPort.authorize(TokenHolder.getToken()
                .substring(DomainConstants.TOKEN_PREFIX.length()));

        if(!Objects.equals(Long.valueOf(user.getId()), restaurant.getOwnerId())){
            throw new RestaurantDoesNotBelongToUserException();
        }
    }
}
