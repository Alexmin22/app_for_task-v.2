package com.example.app_for_task.model.tasks;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

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
    @Column(name = "status")
    @NotEmpty(message = "Заполните поле")
    private Status status;
    @Column(name = "deadline")
    @NotEmpty(message = "Заполните поле")
    private LocalDateTime deadline;
}
