package com.example.app_for_task.model.employee;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private int id;

    @Column(name = "role")
    private String role;

    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public String toString() {
        return role.split("_")[1].toLowerCase();
    }

}
