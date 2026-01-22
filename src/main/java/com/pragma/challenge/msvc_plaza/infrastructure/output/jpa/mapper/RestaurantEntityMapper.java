package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper;

import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);
    List<RestaurantEntity> toEntities(List<Restaurant> restaurantList);
    Restaurant toDomain(RestaurantEntity entity);
    List<Restaurant> toDomains(List<RestaurantEntity> restaurantEntityList);
}
