package com.pragma.challenge.msvc_plaza.application.mapper.response;

import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.RestaurantResponse;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper {
    RestaurantResponse toRespone(Restaurant restaurant);
    List<RestaurantResponse> toResponses(List<Restaurant> restaurantList);
    PageResponse<RestaurantResponse> toResponses(DomainPage<Restaurant> restaurantDomainPage);
}
