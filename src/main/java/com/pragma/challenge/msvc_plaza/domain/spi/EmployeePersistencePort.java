package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.util.filter.EmployeeFilter;

import java.util.List;

public interface EmployeePersistencePort {
    Employee saveEmployee(Employee employee);
    List<Employee> findAll(EmployeeFilter filter);
}
