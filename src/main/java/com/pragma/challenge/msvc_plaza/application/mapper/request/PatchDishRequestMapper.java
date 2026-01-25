package com.pragma.challenge.msvc_plaza.application.mapper.request;

import com.pragma.challenge.msvc_plaza.application.dto.request.PatchDishRequest;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatchDishRequestMapper {
    Dish toDomain(PatchDishRequest request);
    List<Dish> toDomains(List<PatchDishRequest> requestList);
}
