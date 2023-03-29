package com.example.app_for_task.util;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

//@Component
@RequiredArgsConstructor
public class InitClass {

    private final EmployeeService employeeService;


    //@PostConstruct
    public void createUser() {



        Employee employee = new Employee("12345", "user@mail.ru", "User", "Userov", Role.USER);
        Employee employee2 = new Employee("12345", "user2@mail.ru", "User2", "Userov", Role.USER);
        Employee employee3 = new Employee("12345", "user3@mail.ru", "User3", "Userov", Role.USER);
        Employee employee4 = new Employee("12345", "user4@mail.ru", "User4", "Userov", Role.USER);
        Employee employee5 = new Employee("12345", "user5@mail.ru", "User5", "Userov", Role.USER);

        Employee admin = new Employee("00000", "admin@mail.ru", "Admin", "Adminov", Role.ADMIN);

        employeeService.add(employee);
        employeeService.add(employee2);
        employeeService.add(employee3);
        employeeService.add(employee4);
        employeeService.add(employee5);
        employeeService.add(admin);

        System.out.println("Тестовые пользователи созданы");

    }
}