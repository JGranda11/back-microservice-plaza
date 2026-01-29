package com.pragma.challenge.msvc_plaza.application.handler;

import com.pragma.challenge.msvc_plaza.application.dto.request.order.OrderRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderCreatedResponse;

public interface OrderHandler {
    OrderCreatedResponse createOrder(OrderRequest orderRequest);
}
