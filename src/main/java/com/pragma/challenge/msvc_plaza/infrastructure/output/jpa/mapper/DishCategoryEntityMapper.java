package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper;

import com.pragma.challenge.msvc_plaza.domain.model.DishCategory;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishCategoryEntityMapper {
    DishCategoryEntity  toEntity(DishCategory dishCategory);
    List<DishCategoryEntity> toEntities(List<DishCategoryEntity> dishCategories);
    DishCategory toDomain(DishCategoryEntity dishCategoryEntity);
    List<DishCategory> toDomains(List<DishCategoryEntity> categoryEntities);
}
