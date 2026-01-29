package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository;


import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {
    Page<DishEntity> findAll(Specification<DishEntity> specs, Pageable pageable);
}
