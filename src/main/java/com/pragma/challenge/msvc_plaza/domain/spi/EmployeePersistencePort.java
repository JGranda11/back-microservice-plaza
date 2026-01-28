package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;

public interface EmployeePersistencePort {
    Employee saveEmployee(Employee employee);
}
