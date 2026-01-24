package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper {
    DishEntity toEntity(Dish dish);
    List<DishEntity> toEntities(List<Dish> dishList);
    Dish toDomain(DishEntity dishEntity);
    List<Dish> toDomains(List<DishEntity> dishEntityList);
}
