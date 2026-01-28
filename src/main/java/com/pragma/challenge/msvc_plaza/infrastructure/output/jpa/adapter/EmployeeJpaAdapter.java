package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.spi.EmployeePersistencePort;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.EmployeeRepository;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeJpaAdapter  implements EmployeePersistencePort {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;
    private final RestaurantRepository restaurantRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity entity = employeeEntityMapper.toEntity(employee);
        restaurantRepository.findById(employee.getRestaurant().getId())
                .ifPresent(entity::setRestaurant);
        return employeeEntityMapper.toDomain(employeeRepository.save(entity));
    }
}
