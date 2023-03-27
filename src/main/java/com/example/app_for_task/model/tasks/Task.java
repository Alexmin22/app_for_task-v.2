package com.example.app_for_task.model.tasks;

import javax.persistence.*;

import com.example.app_for_task.model.employee.Employee;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
@Setter @Getter
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
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "status")
            private Status status;
    @Column(name = "deadline")
    @NotEmpty(message = "Заполните поле")
    private LocalDate deadline;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tasks_employees",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employeeList;
}
