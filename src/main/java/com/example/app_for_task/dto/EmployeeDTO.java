package com.example.app_for_task.dto;

import com.example.app_for_task.model.employee.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class EmployeeDTO {
    @NotNull(message = "Id не может быть пустым")
    private int id;
    @NotNull(message = "Имя не может быть пустым")
    private String firstName;
    @NotNull(message = "Фамилия не может быть пустой")
    private String lastName;
    private Set<Role> roleSet;
    private List<TaskDTO> taskDTOList;

}
