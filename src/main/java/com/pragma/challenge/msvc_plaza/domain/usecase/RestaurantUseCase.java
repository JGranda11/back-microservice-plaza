package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.*;
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
import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;
import com.pragma.challenge.msvc_plaza.domain.util.filter.DishFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;

import java.util.Objects;

public class RestaurantUseCase implements RestaurantServicePort {
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;
    private final DishPersistencePort dishPersistencePort;

    public RestaurantUseCase(RestaurantPersistencePort restaurantPersistencePort,
                             UserPersistencePort userPersistencePort,
                             EmployeePersistencePort employeePersistencePort,
                             AuthorizationSecurityPort authorizationSecurityPort,
                             DishPersistencePort dishPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
        this.dishPersistencePort = dishPersistencePort;
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

    @Override
    public DomainPage<Restaurant> findPage(PaginationData paginationData) {
        paginationData.setColumn(DomainConstants.NAME_FIELD);

        return restaurantPersistencePort.findAll(paginationData);
    }

    @Override
    public DomainPage<Dish> findDishesOfRestaurant(Long id, String category, PaginationData paginationData) {
        if(restaurantPersistencePort.findById(id) == null){
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(),String.valueOf(id));
        }
        DishFilter filter = DishFilter.builder()
                .restaurantId(id)
                .category(category)
                .state(DishState.ACTIVE)
                .build();

        return dishPersistencePort.findAll(filter, paginationData);
    }

    @Override
    public Restaurant findCurrentOwnerRestaurant() {
        AuthorizedUser owner = authorizationSecurityPort.authorize(TokenHolder.getToken().substring(DomainConstants.TOKEN_PREFIX.length()));
        Restaurant restaurant=  restaurantPersistencePort.findByOwnerId(owner.getId());
        if(restaurant == null) throw new OwnerHasNotRestaurantException();
        return restaurant;
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
        employee.setRestaurant(restaurant);
        if(!Objects.equals(Long.valueOf(user.getId()), restaurant.getOwnerId())){
            throw new RestaurantDoesNotBelongToUserException();
        }
    }
}
