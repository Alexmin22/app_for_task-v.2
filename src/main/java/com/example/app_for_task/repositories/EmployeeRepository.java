package com.example.app_for_task.repositories;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);

    @Query(
            value = "SELECT * FROM employees WHERE role='USER'",
            nativeQuery = true)
    public List<Employee> findEmployeesByRole(String role);
}
