package com.example.app_for_task.repositories;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<Employee> findById(int id);
    Optional<Employee> findEmployeeByFirstName(String name);

}
