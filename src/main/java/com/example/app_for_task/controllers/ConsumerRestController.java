//package com.example.app_for_task.controllers;
//
//import com.example.app_for_task.dto.EmployeeDTO;
//import com.example.app_for_task.dto.EmployeeDTOMapper;
//import com.example.app_for_task.dto.TaskDTO;
//import com.example.app_for_task.dto.TaskDTOMapper;
//import com.example.app_for_task.model.employee.Employee;
//import com.example.app_for_task.model.tasks.Task;
//import com.example.app_for_task.services.EmployeeService;
//import com.example.app_for_task.services.TaskService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/consumer")
//@Validated
//@RequiredArgsConstructor
//public class ConsumerRestController {
//
//    private final TaskService taskService;
//    private final EmployeeService employeeService;
//
//    private final TaskDTOMapper taskDTOMapper;
//    private final EmployeeDTOMapper employeeDTOMapper;
//
//    public EmployeeDTO update(@Validated @RequestBody EmployeeDTO employeeDTO) {
//        Employee cosumer = employeeDTOMapper.fromDtoMap(employeeDTO);
//        Employee consUpdt = employeeService.update(cosumer);
//
//        return employeeDTOMapper.inDtoMap(consUpdt);
//    }
//
//    @GetMapping("/{id}")
//    public EmployeeDTO getById(@PathVariable int id) {
//        Employee con = employeeService.getById(id);
//
//        return employeeDTOMapper.inDtoMap(con);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable int id) {
//        employeeService.delete(id);
//    }
//
//    @GetMapping("/{id}/tasks")
//    public List<TaskDTO> getAllTasksOfUserById(@PathVariable int id) {
//        List<Task> taskList = taskService.findAllTaskById(id);
//
//        return taskDTOMapper.inDtoMap(taskList);
//    }
//
//    @PostMapping("/{id}/tasks")
//    public TaskDTO createTask(@PathVariable int id,
//                              @Validated @RequestBody TaskDTO taskDto) {
//        Task task = taskDTOMapper.fromDtoMap(taskDto);
//        Task createdTask = taskService.create(task, id);
//
//        return taskDTOMapper.inDtoMap(createdTask);
//    }
//}
