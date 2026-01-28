package com.pragma.challenge.msvc_plaza.application.mapper.response;

import com.pragma.challenge.msvc_plaza.application.dto.response.EmployeeResponse;
import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeResponseMapper {
    default Long toRestaurantId(Restaurant restaurant){
        return restaurant.getId();
    }

    @Mapping(target = "restaurantId", source = "restaurant")
    EmployeeResponse toResponse( Employee restaurant);
    List<EmployeeResponse> toResponses(List<Employee> restaurants);
}
