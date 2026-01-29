package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.spi.OrderPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.OrderRepository;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.specification.OrderSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderPersistencePort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        return orderEntityMapper.toDomain(
                orderRepository.save(entity)
        );
    }

    @Override
    public List<Order> findFilteredOrders(OrderFilter filter) {
        Specification<OrderEntity> specs = OrderSpecificationBuilder.filterBy(filter);
        return orderEntityMapper.toDomains(
                orderRepository.findAll(specs)
        );
    }
}
