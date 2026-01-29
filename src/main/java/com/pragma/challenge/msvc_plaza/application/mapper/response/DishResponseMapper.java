package com.pragma.challenge.msvc_plaza.application.mapper.response;

import com.pragma.challenge.msvc_plaza.application.dto.response.DishResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.DishCategory;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishResponseMapper {
    default String toCategoryText(DishCategory category){
        return category.getDescription();
    }
    default Long toRestaurantId(Restaurant restaurant){
        return restaurant.getId();
    }

    @Mapping(target = "restaurantId", source = "restaurant")
    DishResponse toResponse(Dish dish);
    List<DishResponse> toResponses(List<Dish> restaurants);
    PageResponse<DishResponse> toResponses(DomainPage<Dish> dishPage);
}
