package com.example.app_for_task.services;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.repositories.EmployeeRepository;
import com.example.app_for_task.repositories.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(() ->
                new NoSuchElementException("User doesn't exists"));
        return new User(employee.getUsername(),
                employee.getPassword(),
                true, true,
                true, true,
                employee.getRole().getAuthorities());
    }

}
