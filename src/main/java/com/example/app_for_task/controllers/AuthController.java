package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.EmployeeDTO;
import com.example.app_for_task.dto.EmployeeDTOMapper;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {

    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDTOMapper;
    private List<Employee> list;
    private List<EmployeeDTO> listDto;

    @PostConstruct
    public void loadData() {
        list = employeeService.getAllEmployees();

    }

    @GetMapping("/admin")
    public String showEmployees(Model model) {
        model.addAttribute("employees", list);

        return "admin";
    }

//    @GetMapping("/login")
//    public String getLoginPage() {
//        return null;
//    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "index";
    }

//    @PostMapping("/register")
//    public EmployeeDTO register( @RequestBody EmployeeDTO employeeDTO) {
//        Employee employee = employeeService.create(employeeDTOMapper.fromDtoMap(employeeDTO));
//
//        return employeeDTOMapper.inDtoMap(employee);
//    }

    @GetMapping("/hello")
    public String hello() {
        return null;
    }

    @GetMapping("/user")
    public String user() {
        return null;
    }


}
