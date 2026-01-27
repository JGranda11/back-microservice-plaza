package com.pragma.challenge.msvc_plaza.infrastructure.configuration;

import com.pragma.challenge.msvc_plaza.domain.api.DishServicePort;
import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.api.security.AuthorizationServicePort;
import com.pragma.challenge.msvc_plaza.domain.spi.DishCategoryPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.domain.usecase.DishUseCase;
import com.pragma.challenge.msvc_plaza.domain.usecase.RestaurantUseCase;
import com.pragma.challenge.msvc_plaza.domain.usecase.security.AuthorizationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

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
            RestaurantPersistencePort restaurantPersistencePort,
            AuthorizationSecurityPort authorizationSecurityPort
    ){
        return new DishUseCase(
                dishPersistencePort,
                dishCategoryPersistencePort,
                restaurantPersistencePort,
                authorizationSecurityPort
        );
    }

    @Bean
    public AuthorizationServicePort authorizationServicePort(
            AuthorizationSecurityPort authorizationSecurityPort
    ){
        return new AuthorizationUseCase(authorizationSecurityPort);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
