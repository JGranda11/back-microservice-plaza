package com.pragma.challenge.msvc_plaza.infrastructure.configuration;

import com.pragma.challenge.msvc_plaza.domain.api.DishServicePort;
import com.pragma.challenge.msvc_plaza.domain.api.OrderServicePort;
import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.api.security.AuthorizationServicePort;
import com.pragma.challenge.msvc_plaza.domain.spi.*;
import com.pragma.challenge.msvc_plaza.domain.spi.messaging.NotificationSenderPort;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.domain.usecase.DishUseCase;
import com.pragma.challenge.msvc_plaza.domain.usecase.OrderUseCase;
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
            UserPersistencePort userPersistencePort,
            EmployeePersistencePort employeePersistencePort,
            AuthorizationSecurityPort authorizationSecurityPort,
            DishPersistencePort dishPersistencePort){
        return new RestaurantUseCase(
                restaurantPersistencePort,
                userPersistencePort,
                employeePersistencePort,
                authorizationSecurityPort,
                dishPersistencePort
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
    public OrderServicePort orderServicePort(
            OrderPersistencePort orderPersistencePort,
            RestaurantPersistencePort restaurantPersistencePort,
            AuthorizationSecurityPort authorizationSecurityPort,
            DishPersistencePort dishPersistencePort,
            EmployeePersistencePort employeePersistencePort,
            UserPersistencePort userPersistencePort,
            NotificationSenderPort notificationSenderPort
    ){
        return new OrderUseCase(
                orderPersistencePort,
                restaurantPersistencePort,
                authorizationSecurityPort,
                dishPersistencePort,
                employeePersistencePort,
                userPersistencePort,
                notificationSenderPort
        );
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
