package com.pragma.challenge.msvc_plaza.infrastructure.configuration;

import com.pragma.challenge.msvc_plaza.domain.api.DishServicePort;
import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.spi.DishCategoryPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.usecase.DishUseCase;
import com.pragma.challenge.msvc_plaza.domain.usecase.RestaurantUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public RestaurantServicePort restaurantServicePort(
            RestaurantPersistencePort restaurantPersistencePort,
            UserPersistencePort userPersistencePort){
        return new RestaurantUseCase(
                restaurantPersistencePort,
                userPersistencePort
        );
    }

    @Bean
    public DishServicePort dishServicePort(
            DishPersistencePort dishPersistencePort,
            DishCategoryPersistencePort dishCategoryPersistencePort,
            RestaurantPersistencePort restaurantPersistencePort
    ){
        return new DishUseCase(
                dishPersistencePort,
                dishCategoryPersistencePort,
                restaurantPersistencePort
        );
    }
}
