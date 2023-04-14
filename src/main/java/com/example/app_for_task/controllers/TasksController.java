package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.EmployeeDTO;
import com.example.app_for_task.dto.EmployeeDTOMapper;
//import com.example.app_for_task.dto.TaskDTO;
//import com.example.app_for_task.dto.TaskDTOMapper;
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
    private final EmployeeDTOMapper employeeDTOMapper;


    @GetMapping("/admin/home")
    public String adminHome( Model model) {
        List<Employee> listEmp = employeeService.getEmployeesOnly("USER");
        List<EmployeeDTO> list = employeeDTOMapper.inDtoMapList(listEmp);

        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);

        model.addAttribute("list", list);
        model.addAttribute("roles", roles);
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("newTask", new Task());

        return "admin-home";
    }

    @GetMapping("/employee/home")
    public String emplHome(Model model, Principal principal) {
        Employee employee1 = employeeService.getByUsername(principal.getName()).get();
        EmployeeDTO employeeDTO = employeeDTOMapper.inDtoMap(employee1);
        List<Task> taskList = employee1.getTaskList();
        List<Status> statusList = List.of(Status.SCHEDULED_TASK, Status.AT_WORK, Status.TASK_FINISHED);

        model.addAttribute("employee", employeeDTO);
        model.addAttribute("taskList", taskList);
        model.addAttribute("statusList", statusList);

        return "employee-home";
    }

    @PatchMapping("/employee/task/{id}")
    public String updateTaskFromEmpl(Model model, @ModelAttribute("task") Task task,
                             @PathVariable("id") int id) {

        task.setEmployeeList(taskService.getById(id).getEmployeeList());
        task.setEmployeeDTOList(taskService.getById(id).getEmployeeDTOList());

        taskService.update(task, id);

        return "redirect:/employee/home";
    }

    @GetMapping("/admin/alltasks")
    public String allTasks(Model model) {

        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);

        List<Status> statusList = List.of(Status.SCHEDULED_TASK, Status.AT_WORK, Status.TASK_FINISHED);
        List<Employee> list = employeeService.getEmployeesOnly("USER");

        model.addAttribute("tasks", taskService.findAllTask());
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("newTask", new Task());
        model.addAttribute("roles", roles);
        model.addAttribute("emplList", employeeDTOMapper.inDtoMapList(list));
        model.addAttribute("statusList", statusList);

        return "view-all-tasks";
    }

    @PostMapping("/admin/alltasks")
    public String createTask(@RequestParam(value = "deadLine", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadLine,
                             Model model, @ModelAttribute("newTask") Task task) {

        task.setStatus(Status.SCHEDULED_TASK);
        task.setEmployeeList(employeeDTOMapper.fromDtoMapList(task.getEmployeeDTOList()));
        taskService.create(task);

        List<Task> tasks = taskService.findAllTask();
        model.addAttribute("tasks", tasks);

        return "redirect:/admin/alltasks";
    }

    @PatchMapping("/admin/alltasks/{id}")
    public String updateTask(Model model, @ModelAttribute("task") Task task,
                                 @PathVariable("id") int id) {

        task.setEmployeeList(employeeDTOMapper.fromDtoMapList(task.getEmployeeDTOList()));
        taskService.update(task, id);

        List<Task> list = taskService.findAllTask();
        model.addAttribute("listOfTask", list);

        return "redirect:/admin/alltasks";
    }

    @DeleteMapping("/admin/alltasks/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/admin/alltasks";
    }
}
