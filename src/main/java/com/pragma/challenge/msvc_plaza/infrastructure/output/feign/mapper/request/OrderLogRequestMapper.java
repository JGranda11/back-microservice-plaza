package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.request;

import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request.NewOrderLogRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLogRequestMapper {

    default String toRestaurantId(Restaurant restaurant){
        return String.valueOf(restaurant.getId());
    }

    @Mapping(target = "restaurantId", source = "restaurant")
    @Mapping(target = "orderId", source = "id")
    NewOrderLogRequest toRequest(Order response);
}