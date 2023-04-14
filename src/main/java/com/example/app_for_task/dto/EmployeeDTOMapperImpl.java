package com.example.app_for_task.dto;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.convert.converter.Converter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeDTOMapperImpl implements EmployeeDTOMapper, Converter<String, EmployeeDTO> {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO inDtoMap(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.setFirstName( employee.getFirstName() );
        employeeDTO.setLastName( employee.getLastName() );
        employeeDTO.setPassword( employee.getPassword() );
        employeeDTO.setEmail( employee.getEmail() );
        employeeDTO.setRole( employee.getRole() );
        employeeDTO.setTaskDTOList(employee.getTaskList());

        return employeeDTO;
    }

    @Override
    public Employee fromDtoMap(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDTO.getId() );
        employee.setPassword( employeeDTO.getPassword() );
        employee.setEmail( employeeDTO.getEmail() );
        employee.setFirstName( employeeDTO.getFirstName() );
        employee.setLastName( employeeDTO.getLastName() );
        employee.setRole( employeeDTO.getRole() );
        employee.setTaskList(employeeDTO.getTaskDTOList());

        return employee;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> fromDtoMapList(List<EmployeeDTO> employee) {
        if ( employee == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employee.size() );
        for ( EmployeeDTO employeeDTO : employee ) {
            list.add( fromDtoMap( employeeDTO ) );
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> inDtoMapList(List<Employee> employee) {
        if ( employee == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employee.size() );
        for ( Employee employee1 : employee ) {
            list.add( inDtoMap( employee1 ) );
        }

        return list;
    }

    @Override
    public EmployeeDTO convert(String id) {
        Employee employee = employeeRepository.findById(Integer.valueOf(id)).get();

        return inDtoMap(employee);
    }
}
