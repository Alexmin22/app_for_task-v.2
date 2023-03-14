package com.example.app_for_task.dto;

import com.example.app_for_task.model.employee.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeDTOMapper {
    EmployeeDTO inDtoMap(Employee employee);
    Employee fromDtoMap(EmployeeDTO employeeDTO);
}
