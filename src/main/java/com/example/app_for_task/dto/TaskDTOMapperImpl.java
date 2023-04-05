//package com.example.app_for_task.dto;
//
//import com.example.app_for_task.model.tasks.Task;
//import lombok.NonNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class TaskDTOMapperImpl implements TaskDTOMapper {
//
//    private final EmployeeDTOMapper employeeDTOMapper;
//
//    @Autowired
//    public TaskDTOMapperImpl( EmployeeDTOMapper employeeDTOMapper) {
//        this.employeeDTOMapper = employeeDTOMapper;
//    }
//
//    @Override
//    public TaskDTO inDtoMap(Task task) {
//        if (task == null) {
//            return null;
//        }
//
//        TaskDTO taskDTO = new TaskDTO();
//
//        taskDTO.setId(task.getId());
//        taskDTO.setTaskName(task.getTaskName());
//        taskDTO.setTaskDesc(task.getTaskDesc());
//        taskDTO.setStatus(task.getStatus());
//        taskDTO.setDeadline(task.getDeadline());
//        taskDTO.setEmployeeDTOList(employeeDTOMapper.inDtoMapList(task.getEmployeeList()));
//
//        return taskDTO;
//    }
//
//    @Override
//    public Task fromDtoMap(TaskDTO taskDTO) {
//        if (taskDTO == null) {
//            return null;
//        }
//
//        Task task = new Task();
//
//        task.setId(taskDTO.getId());
//        task.setTaskName(taskDTO.getTaskName());
//        task.setTaskDesc(taskDTO.getTaskDesc());
//        task.setStatus(taskDTO.getStatus());
//        task.setDeadline(taskDTO.getDeadline());
//        task.setEmployeeList(employeeDTOMapper.fromDtoMapList(taskDTO.getEmployeeDTOList()));
//
//        return task;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<TaskDTO> inDtoMapList(List<Task> taskList) {
//        if (taskList == null) {
//            return null;
//        }
//
//        List<TaskDTO> dtoList = new ArrayList<>();
//
//        for (Task task : taskList) {
//            dtoList.add(inDtoMap(task));
//        }
//        return dtoList;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Task> fromDtoMapList(List<TaskDTO> taskDTOList) {
//        if (taskDTOList == null) {
//            return null;
//        }
//
//        List<Task> taskList = new ArrayList<>();
//
//        for (TaskDTO task : taskDTOList) {
//            taskList.add(fromDtoMap(task));
//        }
//        return taskList;
//    }
//}
