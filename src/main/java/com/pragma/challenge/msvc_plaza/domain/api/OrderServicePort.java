package com.pragma.challenge.msvc_plaza.domain.api;

import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;

public interface OrderServicePort {
    Order createOrder(Order order);
    DomainPage<Order> findOrders(OrderFilter filter, PaginationData paginationData);
    Order setAssignedEmployee(Long id);
}
