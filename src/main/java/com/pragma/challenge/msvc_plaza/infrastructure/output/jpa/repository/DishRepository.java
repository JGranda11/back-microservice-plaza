package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository;


import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {
}
