package com.example.app_for_task.model.tasks;

import javax.persistence.*;

import com.example.app_for_task.model.employee.Employee;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private int id;
    @Column(name = "task_name")
    @NotEmpty(message = "Заполните поле")
    private String taskName;
    @Column(name = "task_description")
    private String taskDesc;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
            private Status status;
    @Column(name = "deadline")
    @NotEmpty(message = "Заполните поле")
    private LocalDate deadline;
    @ManyToMany
    @JoinTable(
            name = "Employees_Tasks",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Employee> employeeList;
}
