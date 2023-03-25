package com.example.app_for_task.services;

import com.example.app_for_task.model.employee.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserDetailsService {
    void add(Employee user);
    List<Employee> getAllUsers();

    List<Employee> getEmployeesOnly(String role);
    void delete(int id);
    void update(Employee user, int id);
    Employee getById(int id);
    Optional<Employee> getByUsername(String userName);
}
