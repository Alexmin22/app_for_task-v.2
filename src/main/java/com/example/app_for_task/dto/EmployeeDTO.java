package com.example.app_for_task.dto;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.repositories.EmployeeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
@Data
public class EmployeeDTO implements Converter<String, EmployeeDTO> {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOMapper employeeDTOMapper;
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
    private List<TaskDTO> taskDTOList = new ArrayList<>();


    @Override
    public EmployeeDTO convert(String id) {
        Employee employee = employeeRepository.findById(Integer.valueOf(id)).get();

        return employeeDTOMapper.inDtoMap(employee);
    }
}
