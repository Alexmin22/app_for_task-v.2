package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.EmployeeDTO;
import com.example.app_for_task.dto.EmployeeDTOMapper;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/auth/")
@AllArgsConstructor
public class AuthController {

    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDTOMapper;

    @GetMapping("/login")
    public String getLoginPage() {
        return null;
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return null;
    }

    @PostMapping("/register")
    public EmployeeDTO register( @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.create(employeeDTOMapper.fromDtoMap(employeeDTO));

        return employeeDTOMapper.inDtoMap(employee);
    }

}
