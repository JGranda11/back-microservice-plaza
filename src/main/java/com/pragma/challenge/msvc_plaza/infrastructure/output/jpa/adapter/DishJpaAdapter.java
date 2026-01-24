package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.spi.DishPersistencePort;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishEntity;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements DishPersistencePort {
    private final DishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

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
}
