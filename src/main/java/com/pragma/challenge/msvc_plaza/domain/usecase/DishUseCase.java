package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.api.DishServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_plaza.domain.exception.RestaurantDoesNotBelongToUserException;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.DishCategory;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.domain.spi.DishCategoryPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.domain.util.DomainConstants;
import com.pragma.challenge.msvc_plaza.domain.util.TokenHolder;
import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;

import java.util.Objects;

public class DishUseCase implements DishServicePort {

    private final DishPersistencePort dishPersistencePort;
    private final DishCategoryPersistencePort dishCategoryPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;

    public DishUseCase(DishPersistencePort dishPersistencePort,
                       DishCategoryPersistencePort dishCategoryPersistencePort,
                       RestaurantPersistencePort restaurantPersistencePort,
                       AuthorizationSecurityPort authorizationSecurityPort) {
        this.dishPersistencePort = dishPersistencePort;
        this.dishCategoryPersistencePort = dishCategoryPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
    }

    @Override
    public Dish createDish(Dish dish) {

        validateDish(dish);
        dish.setCategory(
                findOrCreateCategory(dish.getCategory().getDescription())
        );
        dish.setState(DishState.ACTIVE);
        return dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish modifyDish(Long id, Dish dish) {
        Dish dishToModify = dishPersistencePort.findById(id);
        if(dishToModify == null){
            throw new EntityNotFoundException(Dish.class.getSimpleName(), id.toString());
        }
        if(dish.getDescription() != null){
            dishToModify.setDescription(dish.getDescription());
        }
        if(dish.getPrice() != null){
            dishToModify.setPrice(dish.getPrice());
        }
        return dishPersistencePort.saveDish(dishToModify);
    }

    @Override
    public Dish changeDishState(Long id, DishState state) {
        Dish dishToModify = findById(id);
        validateDish(dishToModify);
        dishToModify.setState(state);

        return dishPersistencePort.saveDish(dishToModify);
    }

    private DishCategory findOrCreateCategory(String description){
        DishCategory category = dishCategoryPersistencePort.findByDescription(description);
        if(category == null) category = dishCategoryPersistencePort.saveCategory(description);
        return category;
    }

    private void validateDish(Dish dish){
        Restaurant restaurant = restaurantPersistencePort.findById(dish.getRestaurant().getId());
        if(restaurant == null){
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(), String.valueOf(dish.getRestaurant().getId()));
        }
        String rawToken = TokenHolder.getToken();

        String cleanToken = (rawToken != null && rawToken.startsWith(DomainConstants.TOKEN_PREFIX))
                ? rawToken.substring(DomainConstants.TOKEN_PREFIX.length())
                : rawToken;
        AuthorizedUser user = authorizationSecurityPort.authorize(cleanToken);

        if (!Objects.equals(Long.valueOf(user.getId()), restaurant.getOwnerId())){
            throw  new RestaurantDoesNotBelongToUserException();
        }
    }

    private Dish findById(Long id){
        Dish dish = dishPersistencePort.findById(id);
        if(dish == null)
            throw new EntityNotFoundException(Dish.class.getSimpleName(), id.toString());
        return dish;
    }
}
