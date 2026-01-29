package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.specification;

import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;
import com.pragma.challenge.msvc_plaza.domain.util.filter.DishFilter;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.domain.Specification;

public class DishSpecificationBuilder {
    private DishSpecificationBuilder() {
        throw new IllegalStateException("Specification class");
    }

    private static final String ID = "id";
    private static final String RESTAURANT = "restaurant";
    private static final String CATEGORY = "category";
    private static final String STATE = "state";
    private static final String DESCRIPTION = "description";

    public static Specification<DishEntity> filterBy(DishFilter filter) {
        if (filter == null) return null;
        return Specification.where(hasRestaurantId(String.valueOf(filter.getRestaurantId())))
                .and(hasState(filter.getState()))
                .and(hasCategory(filter.getCategory()));
    }

    private static Specification<DishEntity> hasRestaurantId(String restaurantId) {
        return (root, query, criteriaBuilder) ->
                restaurantId == null || restaurantId.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.join(RESTAURANT).get(ID), restaurantId);
    }

    private static Specification<DishEntity> hasState(DishState state) {
        return (root, query, criteriaBuilder) ->
                state == null ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get(STATE), state);
    }

    private static Specification<DishEntity> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null || category.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(root.join(CATEGORY).get(DESCRIPTION), "%" + category + "%");
    }
}
