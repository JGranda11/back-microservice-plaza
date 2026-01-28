package com.pragma.challenge.msvc_plaza.application.mapper.request;

import com.pragma.challenge.msvc_plaza.application.dto.request.EmployeeRequest;
import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeRequestMapper {
    default Restaurant toRestaurant(Long restaurantId){
        return Restaurant.builder()
                .id(restaurantId)
                .build();
    }
    @Mapping(target = "restaurant", source = "restaurantId")
    Employee toDomain(EmployeeRequest request);
    List<Employee> toDomains(List<EmployeeRequest> requests);
}
