package com.pragma.challenge.msvc_plaza.application.mapper.request;

import com.pragma.challenge.msvc_plaza.application.dto.request.RestaurantRequest;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {
    Restaurant toDomain(RestaurantRequest request);
    List<Restaurant> toDomains(List<RestaurantRequest> requestList);
}
