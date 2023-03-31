package com.example.app_for_task.dto;

import com.example.app_for_task.model.employee.Employee;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface EmployeeDTOMapper {
    EmployeeDTO inDtoMap(Employee employee);
    Employee fromDtoMap(EmployeeDTO employeeDTO);
    List<Employee> fromDtoMapList(List<EmployeeDTO> employee);
    List<EmployeeDTO> inDtoMapList(List<Employee> employee);
}
