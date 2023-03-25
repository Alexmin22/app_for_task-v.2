//package com.example.app_for_task.controllers;
//
//import com.example.app_for_task.dto.TaskDTO;
//import com.example.app_for_task.dto.TaskDTOMapper;
//import com.example.app_for_task.model.tasks.Task;
//import com.example.app_for_task.services.TaskService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/tasks")
//@Validated
//@RequiredArgsConstructor
//public class TaskRestController {
//
//    private final TaskService taskService;
//    private final TaskDTOMapper taskDTOMapper;
//
//    @GetMapping("/{id}")
//    public TaskDTO getById(@PathVariable int id) {
//        Task task = taskService.getById(id);
//
//        return taskDTOMapper.inDtoMap(task);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable int id) {
//        taskService.delete(id);
//    }
//
//
//    @PutMapping
//    public TaskDTO update(@Validated @RequestBody TaskDTO taskDTO) {
//        Task task = taskDTOMapper.fromDtoMap(taskDTO);
//        Task updTask = taskService.update(task);
//
//        return taskDTOMapper.inDtoMap(updTask);
//    }
//}