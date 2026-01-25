package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.api.DishServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.DishCategory;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.spi.DishCategoryPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;

public class DishUseCase implements DishServicePort {

    private final DishPersistencePort dishPersistencePort;
    private final DishCategoryPersistencePort dishCategoryPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public DishUseCase(DishPersistencePort dishPersistencePort,
                       DishCategoryPersistencePort dishCategoryPersistencePort,
                       RestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.dishCategoryPersistencePort = dishCategoryPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
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
    }
}
