package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.util.filter.DishFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish findById(Long id);
    DomainPage<Dish> findAll(DishFilter filter, PaginationData paginationData);
}
