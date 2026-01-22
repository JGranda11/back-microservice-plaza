package com.pragma.challenge.msvc_plaza.infrastructure.configuration;

import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
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
}
