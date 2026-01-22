package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository;

import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByNit(String nit);
}
