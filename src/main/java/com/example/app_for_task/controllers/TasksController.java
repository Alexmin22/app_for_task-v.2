package com.example.app_for_task.controllers;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final ConnectTaskWithEmployee connect;

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

    @RequestMapping(value = "/admin/savetask", method = RequestMethod.POST)
    public String createTask(@RequestParam(value = "deadLine", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadLine,
                             Model model, @ModelAttribute("task") Task task) {

        //List<Employee> emplList = employeeService.getEmployeesOnly("USER");

        taskService.create(task);

        List<Task> tasks = taskService.findAllTask();
        model.addAttribute("tasks", tasks);
        //model.addAttribute("emplList", emplList);
        return "view-all-tasks";
    }

    @RequestMapping("/admin/{id}/edittask")
    public String editTask(Model model, @PathVariable(name = "id") int id) {
        List<Status> statusList = List.of(Status.SCHEDULED_TASK, Status.AT_WORK, Status.TASK_FINISHED);
        //List<Employee> emplList = taskService.getById(id).getEmployeeList();
        List<Employee> list = employeeService.getEmployeesOnly("USER");

        model.addAttribute("emplList", list);
        model.addAttribute("task", taskService.getById(id));
        model.addAttribute("statusList", statusList);

        return "edit-task";
    }

    @PatchMapping("/admin/task/{id}")
    public String updateTask(Model model, @ModelAttribute("task") Task task,
                                 @PathVariable("id") int id) {

        taskService.update(task);
        List<Task> list = taskService.findAllTask();
        model.addAttribute("list", list);

        return "redirect:/admin/alltasks";
    }

    @DeleteMapping("/admin/task/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/admin/alltasks";
    }
}
