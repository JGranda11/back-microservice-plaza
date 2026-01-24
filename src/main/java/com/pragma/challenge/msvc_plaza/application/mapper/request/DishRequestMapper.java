package com.pragma.challenge.msvc_plaza.application.mapper.request;

import com.pragma.challenge.msvc_plaza.application.dto.request.DishRequest;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.DishCategory;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishRequestMapper {

    default DishCategory toCategory(String category){
        return DishCategory.builder()
                .description(category)
                .build();
    }

    default Restaurant toRestaurant(Long restaurantId){
        return Restaurant.builder()
                .id(restaurantId)
                .build();
    }

    @Mapping(target = "restaurant", source = "restaurantId")
    Dish toDomain(DishRequest dishRequest);
    List<Dish> toDomains(List<DishRequest> dishRequestList);
}
