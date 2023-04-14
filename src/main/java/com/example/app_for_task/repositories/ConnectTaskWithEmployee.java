package com.example.app_for_task.repositories;

import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.tasks.Task;
import com.example.app_for_task.services.EmployeeService;
import com.example.app_for_task.services.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConnectTaskWithEmployee {

    private final EmployeeService employeeService;

    public List<Task> findAllTaskByEmplId(int emplId) {
        Employee employee = employeeService.getById(emplId);

        return employee.getTaskList();
    }

}