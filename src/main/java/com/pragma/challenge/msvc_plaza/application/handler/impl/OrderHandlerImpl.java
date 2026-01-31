package com.pragma.challenge.msvc_plaza.application.handler.impl;

import com.pragma.challenge.msvc_plaza.application.dto.request.filter.OrderFilterRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.order.OrderRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PaginationRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderCreatedResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderResponse;
import com.pragma.challenge.msvc_plaza.application.handler.OrderHandler;
import com.pragma.challenge.msvc_plaza.application.mapper.request.OrderRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.request.PaginationRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.response.OrderResponseMapper;
import com.pragma.challenge.msvc_plaza.domain.api.OrderServicePort;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements OrderHandler {
    private final OrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    @Override
    public OrderCreatedResponse createOrder(OrderRequest orderRequest) {
        Order order = orderRequestMapper.toDomain(orderRequest);

        return orderResponseMapper.toCreatedResponse(
                orderServicePort.createOrder(order)
        );
    }

    @Override
    public PageResponse<OrderResponse> findOrders(OrderFilterRequest filter, PaginationRequest paginationRequest) {
        OrderFilter orderFilter = OrderFilter
                .builder()
                .states(filter.getStates())
                .build();
        PaginationData paginationData = paginationRequestMapper.toDomain(paginationRequest);
        return orderResponseMapper.toResponses(
                orderServicePort.findOrders(orderFilter,paginationData)
        );
    }

    @Override
    public OrderResponse setAssignedEmployee(Long id) {
        return orderResponseMapper.toResponse(
                orderServicePort.setAssignedEmployee(id)
        );
    }

    @Override
    public OrderResponse setOrderAsDone(Long id) {
        return orderResponseMapper.toResponse(
                orderServicePort.setOrderAsDone(id)
        );
    }
}
