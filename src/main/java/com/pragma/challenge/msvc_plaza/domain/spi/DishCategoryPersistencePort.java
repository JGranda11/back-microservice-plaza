package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.DishCategory;

public interface DishCategoryPersistencePort {
    DishCategory findByDescription (String description);
    DishCategory saveCategory(String description);
}
