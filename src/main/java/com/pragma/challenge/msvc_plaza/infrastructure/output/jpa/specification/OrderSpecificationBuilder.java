package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.specification;

import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import com.pragma.challenge.msvc_plaza.domain.util.filter.OrderFilter;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class OrderSpecificationBuilder {
    private OrderSpecificationBuilder() {
        throw new IllegalStateException("Specification class");
    }

    private static final String ID = "id";
    private static final String RESTAURANT = "restaurant";
    private static final String EMPLOYEE = "assignedEmployee";
    private static final String CUSTOMER_ID = "customerId";
    private static final String STATE = "state";

    public static Specification<OrderEntity> filterBy(OrderFilter filter) {
        if (filter == null) return null;
        return Specification.where(hasAnyState(filter.getStates()))
                .and(hasRestaurantId(filter.getRestaurantId()))
                .and(hasCustomerId(filter.getCustomerId()))
                .and(hasEmployeeId(filter.getAssignedEmployeeId()));
    }

    private static Specification<OrderEntity> hasCustomerId(String customerId) {
        return (root, query, criteriaBuilder) ->
                customerId == null || customerId.isEmpty() ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get(CUSTOMER_ID), customerId);
    }

    private static Specification<OrderEntity> hasRestaurantId(String restaurantId) {
        return (root, query, criteriaBuilder) ->
                restaurantId == null || restaurantId.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.join(RESTAURANT).get(ID), restaurantId);
    }

    private static Specification<OrderEntity> hasEmployeeId(String employeeId) {
        return (root, query, criteriaBuilder) ->
                employeeId == null || employeeId.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.join(EMPLOYEE).get(ID), employeeId);
    }

    private static Specification<OrderEntity> hasAnyState(List<OrderState> states) {
        return states == null || states.isEmpty()
                ? null
                : Specification.anyOf(states.stream().map(OrderSpecificationBuilder::hasState).toList());
    }

    private static Specification<OrderEntity> hasState(OrderState state) {
        return (root, query, criteriaBuilder) ->
                state == null ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get(STATE), state);
    }

}
