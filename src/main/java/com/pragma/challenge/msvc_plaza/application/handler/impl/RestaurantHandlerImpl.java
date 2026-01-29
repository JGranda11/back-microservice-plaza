package com.pragma.challenge.msvc_plaza.application.handler.impl;

import com.pragma.challenge.msvc_plaza.application.dto.request.EmployeeRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.RestaurantRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PaginationRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.EmployeeResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.RestaurantResponse;
import com.pragma.challenge.msvc_plaza.application.handler.RestaurantHandler;
import com.pragma.challenge.msvc_plaza.application.mapper.request.EmployeeRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.request.PaginationRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.request.RestaurantRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.response.EmployeeResponseMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.response.RestaurantResponseMapper;
import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements RestaurantHandler {
    private final RestaurantServicePort restaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;
    private final EmployeeRequestMapper employeeRequestMapper;
    private final EmployeeResponseMapper employeeResponseMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRequestMapper.toDomain(restaurantRequest);
        return restaurantResponseMapper.toRespone(
                restaurantServicePort.createRestaurant(restaurant));
    }

    @Override
    public EmployeeResponse registerEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRequestMapper.toDomain(employeeRequest);

        return employeeResponseMapper.toResponse(restaurantServicePort.registerEmployee(employee));
    }

    @Override
    public PageResponse<RestaurantResponse> findPage(PaginationRequest pagination) {
        PaginationData paginationData = paginationRequestMapper.toDomain(pagination);
        return restaurantResponseMapper.toResponses(
                restaurantServicePort.findPage(paginationData)
        );
    }
}
