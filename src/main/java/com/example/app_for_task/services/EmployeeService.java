package com.example.app_for_task.services;

import com.example.app_for_task.model.employee.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getById(int id);
    Employee getByName(String name);

    void create(Employee employee);
    Employee update(Employee employee);
    void delete(int id);

    List<Employee> getAllEmployees();

}
