package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper {
    DishEntity toEntity(Dish dish);
    List<DishEntity> toEntities(List<Dish> dishList);
    Dish toDomain(DishEntity dishEntity);
    List<Dish> toDomains(List<DishEntity> dishEntityList);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "count", source = "numberOfElements")
    @Mapping(target = "totalCount", source = "totalElements")
    @Mapping(target = "content", source = "content")
    DomainPage<Dish> toDomains(Page<DishEntity> dishEntities);
}
