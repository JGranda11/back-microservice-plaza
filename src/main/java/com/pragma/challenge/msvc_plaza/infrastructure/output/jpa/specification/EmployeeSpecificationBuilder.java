package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.specification;

import com.pragma.challenge.msvc_plaza.domain.util.filter.EmployeeFilter;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecificationBuilder {
    private EmployeeSpecificationBuilder() {
        throw new IllegalStateException("Specification class");
    }

    private static final String ID = "id";
    private static final String RESTAURANT = "restaurant";

    public static Specification<EmployeeEntity> filterBy(EmployeeFilter filter) {
        if (filter == null) return null;
        return Specification.where(hasRestaurantId(filter.getRestaurantId()))
                .and(hasId(filter.getEmployeeId()));
    }

    private static Specification<EmployeeEntity> hasRestaurantId(String restaurantId) {
        return (root, query, criteriaBuilder) ->
                restaurantId == null || restaurantId.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.join(RESTAURANT).get(ID), restaurantId);
    }
    private static Specification<EmployeeEntity> hasId(String id) {
        return (root, query, criteriaBuilder) ->
                id == null || id.isEmpty() ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get(ID), id);
    }

}
