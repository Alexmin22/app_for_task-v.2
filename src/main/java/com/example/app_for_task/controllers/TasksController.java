package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.EmployeeDTO;
import com.example.app_for_task.dto.EmployeeDTOMapper;
import com.example.app_for_task.dto.TaskDTO;
import com.example.app_for_task.dto.TaskDTOMapper;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.model.tasks.Status;
import com.example.app_for_task.model.tasks.Task;
import com.example.app_for_task.repositories.ConnectTaskWithEmployee;
import com.example.app_for_task.services.EmployeeService;
import com.example.app_for_task.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final TaskDTOMapper taskDTOMapper;
    private final EmployeeDTOMapper employeeDTOMapper;


    @GetMapping("/admin/home")
    public String adminHome( Model model) {
        List<Employee> list = employeeService.getEmployeesOnly("USER");
        model.addAttribute("list", list);

        return "admin-home";
    }

    @GetMapping("/employee/home")
    public String emplHome(Model model, Principal principal) {
        Employee employee1 = employeeService.getByUsername(principal.getName()).get();
        EmployeeDTO employeeDTO = employeeDTOMapper.inDtoMap(employee1);
        List<TaskDTO> taskDTOList = taskDTOMapper.inDtoMapList(employee1.getTaskList());
        model.addAttribute("employee", employeeDTO);
        model.addAttribute("taskDTOList", taskDTOList);

        return "employee-home";
    }

    @RequestMapping("/admin/alltasks")
    public String allTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTask());

        return "view-all-tasks";
    }

    @RequestMapping("/admin/createtask")
    public String newTask(Model model, @ModelAttribute("task") Task task) {
        List<Employee> employees = employeeService.getEmployeesOnly("USER");

        model.addAttribute("employeeList", employees);
        model.addAttribute("task", task);

        return "create-task";
    }

    @PostMapping("/admin/savetask")
    public String createTask(@RequestParam(value = "deadLine", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadLine,
                             Model model, @ModelAttribute("task") Task task) {

        taskService.create(task);

        List<Task> tasks = taskService.findAllTask();
        model.addAttribute("tasks", tasks);
        return "view-all-tasks";
    }

    @RequestMapping("/admin/{id}/edittask")
    public String editTask(Model model, @PathVariable(name = "id") int id) {

        List<Status> statusList = List.of(Status.SCHEDULED_TASK, Status.AT_WORK, Status.TASK_FINISHED);
        List<Employee> list = employeeService.getEmployeesOnly("USER");
        TaskDTO taskDTO = taskDTOMapper.inDtoMap(taskService.getById(id));
        List<EmployeeDTO> employeeDTOList = employeeDTOMapper.inDtoMapList(taskService.getById(id).getEmployeeList());
        taskDTO.setEmployeeDTOList(employeeDTOList);

        model.addAttribute("emplList", employeeDTOMapper.inDtoMapList(list));
        model.addAttribute("taskDTO", taskDTO);
        model.addAttribute("statusList", statusList);

        return "edit-task";
    }

    @PatchMapping("/admin/task/{id}")
    public String updateTask(Model model, @ModelAttribute("task") TaskDTO task,
                                 @PathVariable("id") int id) {

        Task task1 = taskDTOMapper.fromDtoMap(task);
        task1.setEmployeeList(employeeDTOMapper.fromDtoMapList(task.getEmployeeDTOList()));
        taskService.update(task1);
        List<Task> list = taskService.findAllTask();
        model.addAttribute("list", list);

        return "redirect:/admin/alltasks";
    }

    @DeleteMapping("/admin/task/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/admin/alltasks";
    }

    @RequestMapping("/employee/{id}/edittask")
    public String editEmplTask(Model model, @PathVariable(name = "id") int id) {
        List<Status> statusList = List.of(Status.SCHEDULED_TASK, Status.AT_WORK, Status.TASK_FINISHED);

        TaskDTO taskDTO = taskDTOMapper.inDtoMap(taskService.getById(id));
        List<EmployeeDTO> employeeDTOList = employeeDTOMapper.inDtoMapList(taskService.getById(id).getEmployeeList());
        model.addAttribute("task", taskDTO);
        model.addAttribute("statusList", statusList);
        model.addAttribute("emplDTOList", employeeDTOList);

        return "edit-task-empl";
    }

    @PatchMapping("/employee/task/{id}")
    public String updateEmplTask(Model model, @ModelAttribute("task") TaskDTO task,
                             @PathVariable("id") int id) {

        Task currentTask = taskService.getById(id);
        currentTask.setStatus(task.getStatus());
        taskService.update(currentTask);

        return "redirect:/employee/home";
    }
}
