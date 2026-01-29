package com.pragma.challenge.msvc_plaza.application.mapper.response;

import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderCreatedResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderDishResponse;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.model.order.OrderDish;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderResponseMapper {
    default Long toDishId(Dish dish){
        return dish.getId();
    }
    default Long toRestaurantId(Restaurant restaurant){
        return restaurant.getId();
    }

    @Mapping(target = "dishId", source = "dish")
    OrderDishResponse toResponse(OrderDish orderDish);

    @Mapping(target = "restaurantId", source = "restaurant")
    OrderCreatedResponse toCreatedResponse(Order restaurant);
    PageResponse<OrderCreatedResponse> toResponses(DomainPage<Order> restaurantsPage);
}
