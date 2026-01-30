package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.spi.EmployeePersistencePort;
import com.pragma.challenge.msvc_plaza.domain.util.filter.EmployeeFilter;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.EmployeeRepository;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.RestaurantRepository;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.specification.EmployeeSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeJpaAdapter  implements EmployeePersistencePort {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity entity = employeeEntityMapper.toEntity(employee);
        return employeeEntityMapper.toDomain(
                employeeRepository.save(entity)
        );
    }

    @Override
    public List<Employee> findAll(EmployeeFilter filter) {
        Specification<EmployeeEntity> specs = EmployeeSpecificationBuilder.filterBy(filter);
        return employeeEntityMapper.toDomains(
                employeeRepository.findAll(specs)
        );
    }

    @Override
    public Employee findById(String id) {
        return employeeEntityMapper.toDomain(
                employeeRepository.findById(id).orElse(null)
        );
    }
}
