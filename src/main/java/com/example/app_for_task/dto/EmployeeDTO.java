package com.example.app_for_task.dto;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.model.tasks.Task;
import com.example.app_for_task.repositories.EmployeeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

//    private final EmployeeRepository employeeRepository;
//    private final EmployeeDTOMapper employeeDTOMapper;
    @NotNull(message = "Id не может быть пустым")
    private int id;
    @NotNull(message = "Имя не может быть пустым")
    private String firstName;
    @NotNull(message = "Фамилия не может быть пустой")
    private String lastName;

    private String password;

    private String confirmPassword;
    @NotNull
    private String email;
    private Role role;
    private List<Task> taskDTOList = new ArrayList<>();



}
