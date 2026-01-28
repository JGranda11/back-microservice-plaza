package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {
    EmployeeEntity toEntity(Employee employee);
    List<EmployeeEntity> toEntities(List<Employee> employees);
    Employee toDomain(EmployeeEntity entity);
    List<Employee> toDomains(List<EmployeeEntity> entities);
}
