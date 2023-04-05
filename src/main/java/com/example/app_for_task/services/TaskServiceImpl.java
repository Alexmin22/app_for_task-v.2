package com.example.app_for_task.services;

import com.example.app_for_task.dto.EmployeeDTOMapper;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.repositories.ConnectTaskWithEmployee;
import com.example.app_for_task.repositories.NoSuchElementException;
import com.example.app_for_task.model.tasks.Status;
import com.example.app_for_task.model.tasks.Task;
import com.example.app_for_task.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ConnectTaskWithEmployee connectTaskWithEmployee;
    private final EmployeeDTOMapper employeeDTOMapper;

    @Override
    @Transactional
    public Task create(Task task) {

        System.out.println(task.getEmployeeList().toString() + " employee " + task.getEmployeeDTOList().toString()+" employeeDTO */*//*//*/*/**//**/*/*/*/**/*/*/*/*/**/*/*/*/*/*");
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Task task, int id) {
        Task oldTask = getById(id);
        oldTask.setTaskName(task.getTaskName());
        oldTask.setDeadline(task.getDeadline());
        oldTask.setStatus(task.getStatus());
        oldTask.setTaskDesc(task.getTaskDesc());
        oldTask.setEmployeeDTOList(task.getEmployeeDTOList());
        oldTask.setEmployeeList(task.getEmployeeList());
        return taskRepository.save(oldTask);
    }

    @Override
    @Transactional
    public void delete(int taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getById(int taskId) {

        System.out.println(taskId + "task ID");
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Задача не обнаружена"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAllTaskById(int consId) {

        return connectTaskWithEmployee.findAllTaskByEmplId(consId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

}
