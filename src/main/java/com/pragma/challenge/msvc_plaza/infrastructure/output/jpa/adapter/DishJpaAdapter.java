package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.util.filter.DishFilter;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishEntity;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper.PaginationJpaMapper;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.DishRepository;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.specification.DishSpecificationBuilder;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.util.PaginationJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements DishPersistencePort {
    private final DishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;
    private final PaginationJpaMapper paginationJpaMapper;

    @Override
    public Dish saveDish(Dish dish) {
        DishEntity entity = dishEntityMapper.toEntity(dish);
        return dishEntityMapper.toDomain(
                dishRepository.save(entity));
    }

    @Override
    public Dish findById(Long id) {
        return dishEntityMapper.toDomain(
                dishRepository.findById(id).orElse(null)
        );
    }

    @Override
    public DomainPage<Dish> findAll(DishFilter filter, PaginationData paginationData) {
        Specification<DishEntity> specs = DishSpecificationBuilder.filterBy(filter);
        PaginationJpa paginationJpa = paginationJpaMapper.toJpa(paginationData);
        return dishEntityMapper.toDomains(
                dishRepository.findAll(specs, paginationJpa.createPageable())
        );
    }
}
