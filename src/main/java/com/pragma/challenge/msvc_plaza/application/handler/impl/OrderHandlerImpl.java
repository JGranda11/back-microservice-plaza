package com.pragma.challenge.msvc_plaza.application.handler.impl;

import com.pragma.challenge.msvc_plaza.application.dto.request.order.OrderRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderCreatedResponse;
import com.pragma.challenge.msvc_plaza.application.handler.OrderHandler;
import com.pragma.challenge.msvc_plaza.application.mapper.request.OrderRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.response.OrderResponseMapper;
import com.pragma.challenge.msvc_plaza.domain.api.OrderServicePort;
import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements OrderHandler {
    private final OrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    @Override
    public OrderCreatedResponse createOrder(OrderRequest orderRequest) {
        Order order = orderRequestMapper.toDomain(orderRequest);

        return orderResponseMapper.toCreatedResponse(
                orderServicePort.createOrder(order)
        );
    }
}
