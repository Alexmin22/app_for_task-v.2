package com.example.app_for_task.services;

import com.example.app_for_task.model.employee.Employee;

public interface EmployeeService {
    Employee getById(int id);
    Employee getByName(String name);

    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(int id);

}
