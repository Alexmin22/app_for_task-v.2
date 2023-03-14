package com.example.app_for_task.util;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class InitClass {

    private final EmployeeService employeeService;


    public void createUser() {

       // String firstName, String lastName, String password, Set<Role> setroles;
        Role role = new Role();
        role.setRole("ROLE_USER");
        Set<Role> set = new HashSet();
        set.add(role);

        Employee employee = new Employee(1, "User", "Userov", "12345", set, null);

        Role role2 = new Role();
        role2.setRole("ROLE_ADMIN");
        Set<Role> setTwo = new HashSet();
        setTwo.add(role2);
        setTwo.add(role);
        Employee admin = new Employee(2, "Admin", "Adminov", "12345", setTwo, null);

        employeeService.create(employee);
        employeeService.create(admin);


    }
}