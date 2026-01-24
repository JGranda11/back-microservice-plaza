package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.DishCategory;
import com.pragma.challenge.msvc_plaza.domain.spi.DishCategoryPersistencePort;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishCategoryEntity;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper.DishCategoryEntityMapper;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.DishCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryJpaAdapter implements DishCategoryPersistencePort {
    private final DishCategoryEntityMapper dishCategoryEntityMapper;
    private final DishCategoryRepository dishCategoryRepository;
    @Override
    public DishCategory findByDescription(String description) {
        return dishCategoryEntityMapper.toDomain(
                dishCategoryRepository.findByDescription(description).orElse(null)
        );
    }

    @Override
    public DishCategory saveCategory(String description) {
        return dishCategoryEntityMapper.toDomain(dishCategoryRepository.save(
                DishCategoryEntity.builder()
                        .description(description)
                        .build()
        ));
    }
}
