package com.pragma.challenge.msvc_plaza.application.mapper.request;

import com.pragma.challenge.msvc_plaza.application.dto.request.order.OrderDishRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.order.OrderRequest;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.model.order.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapper {

    String RESTAURANT = "restaurant";
    String RESTAURANT_ID = "restaurantId";
    String DISH = "dish";
    String DISH_ID = "dishId";

    default Restaurant toRestaurant(Long restaurantId){
        return Restaurant.builder()
                .id(restaurantId)
                .build();
    }
    @Mapping(target = RESTAURANT, source = RESTAURANT_ID)
    Order toDomain(OrderRequest request);
    List<Order> toDomains(List<OrderRequest> requests);

    // OrderDishes
    default Dish toDish(Long dishId){
        return Dish.builder()
                .id(dishId)
                .build();
    }

    @Mapping(target = DISH, source = DISH_ID)
    OrderDish toDomain(OrderDishRequest orderDishRequest);
}
