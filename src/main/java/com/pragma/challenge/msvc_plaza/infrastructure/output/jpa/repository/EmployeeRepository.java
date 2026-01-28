package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository;

import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
}
