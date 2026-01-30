package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;

import java.util.List;

public interface OrderPersistencePort {
    Order saveOrder(Order order);
    List<Order> findFilteredOrders(OrderFilter filter);
    DomainPage<Order> findOrders(OrderFilter filter, PaginationData paginationData);
}
