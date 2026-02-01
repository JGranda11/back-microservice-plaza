package com.pragma.challenge.msvc_plaza.application.handler;

import com.pragma.challenge.msvc_plaza.application.dto.request.OrderPinRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.filter.OrderFilterRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.order.OrderRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PaginationRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderCreatedResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderResponse;

public interface OrderHandler {
    OrderCreatedResponse createOrder(OrderRequest orderRequest);
    PageResponse<OrderResponse> findOrders(OrderFilterRequest filter, PaginationRequest paginationRequest);
    OrderResponse setAssignedEmployee(Long id);
    OrderResponse setOrderAsDone(Long id);
    OrderResponse setOrderAsDelivered(Long id, OrderPinRequest pinRequest);
}
