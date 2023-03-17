package com.example.app_for_task.services;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.repositories.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee employee = employeeService.getByName(username);

        if(employee.getFirstName() == null){
            throw new NoSuchElementException("Пользователь не авторизован");
        }

        List<GrantedAuthority> authorityList = employee.getRoleSet().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());

        return  new User(employee.getFirstName(),
                employee.getPassword(), authorityList);
    }

}
