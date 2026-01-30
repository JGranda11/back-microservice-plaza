package com.pragma.challenge.msvc_plaza.application.mapper.response;

import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderCreatedResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderDishResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderResponse;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.model.order.OrderDish;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderResponseMapper {

    default Long toDishId(Dish dish){
        return dish != null ? dish.getId() : null;
    }

    default Long toRestaurantId(Restaurant restaurant){
        return restaurant != null ? restaurant.getId() : null;
    }
    default Long toEmployeeId(Employee employee){
        return employee == null ? null: employee.getId();
    }


    @Mapping(target = "dishId", source = "dish")
    OrderDishResponse toResponse(OrderDish orderDish);

    @Mapping(target = "restaurantId", source = "restaurant")
    OrderCreatedResponse toCreatedResponse(Order order);

    @Mapping(target = "restaurantId", source = "restaurant")
    @Mapping(target = "assignedEmployeeId", source = "assignedEmployee")
    @Mapping(target = "dishes", source = "dishes")
    OrderResponse toResponse(Order order);

    // Editado: Implementación por defecto para evitar 'content: null'
    default PageResponse<OrderResponse> toResponses(DomainPage<Order> domainPage) {
        if (domainPage == null) {
            return null;
        }

        List<OrderResponse> responses = new ArrayList<>();
        if (domainPage.getContent() != null) {
            responses = domainPage.getContent().stream()
                    .map(this::toResponse)
                    .toList();
        }

        return PageResponse.<OrderResponse>builder()
                .page(domainPage.getPage())
                .pageSize(domainPage.getPageSize())
                .totalPages(domainPage.getTotalPages())
                .totalCount(domainPage.getTotalCount())
                .count(responses.size())
                .content(responses)
                .build();
    }
}