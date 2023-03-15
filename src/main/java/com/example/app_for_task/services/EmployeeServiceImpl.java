package com.example.app_for_task.services;

import com.example.app_for_task.repositories.NoSuchElementException;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public Employee getById(int id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getByName(String name) {
        return employeeRepository.findEmployeeByFirstName(name)
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
    }

    @Override
    @Transactional
    public void create(Employee employee) {

        if (employeeRepository.findEmployeeByFirstName(employee.getFirstName()).isPresent()) {
            throw new IllegalStateException("Пользователь с таким именем уже существует");
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);

    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);

        return employee;
    }

    @Override
    @Transactional
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
