package com.example.app_for_task.services;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public void add(Employee employee) {

        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalStateException("Пользователь с таким именем уже существует");
        }

        employee.setPassword(passwordEncoder.encode("12345"));
        employeeRepository.save(employee);

    }
    @Override
    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesOnly(String role) {
        return employeeRepository.findEmployeesByRole(role);

    }

    @Override
    public Employee getById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Optional<Employee> getByUsername(String email) {

            return employeeRepository.findByEmail(email);
        }


    @Override
    @Transactional
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Employee employee, int id) {
        Employee oldUser = getById(id);

        if (oldUser.getPassword() == null || oldUser.getPassword().length() < 4) {
            employee.setPassword(passwordEncoder.encode("12345"));
        }

        employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Optional<Employee> userPrimary = getByUsername(firstName);
        if (userPrimary.isEmpty()) {
            throw new UsernameNotFoundException(firstName + " not found");
        }
        return userPrimary.get();
    }
}
