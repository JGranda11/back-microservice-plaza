package com.pragma.challenge.msvc_plaza.domain.api;

import com.pragma.challenge.msvc_plaza.domain.model.order.Order;

public interface OrderServicePort {
    Order createOrder(Order order);
}
