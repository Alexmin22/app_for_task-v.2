package com.example.app_for_task.model.employee;

import com.example.app_for_task.model.tasks.Task;
import javax.persistence.*;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table(name = "employees")
@Entity
@Setter @Getter
@NoArgsConstructor
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "password")
    @Size(min = 5, message = "Длина пароля не менее 5 символов")
    @NotNull
    private String password;

    @Transient
    private String confirmPassword;

    @Column(unique = true, name = "email")
    @Email
    @NotNull
    private String email;

    @Column(name = "firstName")
    @NotEmpty(message = "Заполните имя")
    @Size(min = 2, max = 20, message = "Введите имя от 2 до 20 символов")
    @NotNull
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "Фамилия пуста")
    @Size(min = 2, message = "Введите фамилию")
    @NotNull
    private String lastName;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "role")
    private Role role;

    public Employee(String password, String email, String firstName, String lastName, Role role) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tasks_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> taskList;
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  role.getAuthorities();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee user = (Employee) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
