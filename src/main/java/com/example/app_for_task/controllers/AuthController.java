package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.EmployeeDTO;
import com.example.app_for_task.dto.EmployeeDTOMapper;
import com.example.app_for_task.dto.TaskDTO;
import com.example.app_for_task.dto.TaskDTOMapper;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {

    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDTOMapper;


    @GetMapping("/api")
    public String hello() {
        return "index";
    }


    @RequestMapping("/employee/{id}/editemployee")
    public String editEmployeeSelf(Model model, @PathVariable(name = "id") int id) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employeeDTOMapper.inDtoMap(employee));

        return "edit-employee-self";
    }

    @PatchMapping("/employee/{id}")
    public String updateEmployeeSelf(@ModelAttribute("employee") EmployeeDTO empPass,
                                 @PathVariable("id") int id) {

        Employee currentEmployee = employeeService.getById(id);

        if (!empPass.getPassword().equals(empPass.getConfirmPassword()) || empPass.getPassword()==null ||
                empPass.getPassword().length() <= 4) {
            empPass.setPassword(" ");
            empPass.setConfirmPassword(" ");

            return "edit-employee-self";
        }
        currentEmployee.setPassword(empPass.getPassword());
        employeeService.update(currentEmployee, id);
        empPass.setPassword(" ");
        empPass.setConfirmPassword(" ");

        return "redirect:/employee/home";
    }

    @RequestMapping("/admin/createemployee")
    public String newEmployee(Model model, @ModelAttribute("employee") EmployeeDTO employee) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);

        model.addAttribute("employee", employee);
        model.addAttribute("roles", roles);
        return "create-employee";
    }

    @GetMapping("/admin/saveempl")
    public String createEmployee(Model model, @ModelAttribute("employee") EmployeeDTO employee) {
        if (employee.getPassword()==null) {
            employee.setPassword("12345");
        }
        employee.setConfirmPassword(employee.getPassword());
        employeeService.add(employeeDTOMapper.fromDtoMap(employee));

        List<Employee> list = employeeService.getEmployeesOnly("USER");
        model.addAttribute("list", list);
        return "admin-home";
    }

    @RequestMapping("/admin/{id}/editemployee")
    public String editEmployee(Model model, @PathVariable(name = "id") int id) {
        model.addAttribute("employee", employeeDTOMapper.inDtoMap(employeeService.getById(id)));
        return "edit-employee";
    }

    @PatchMapping("/admin/{id}")
    public String updateEmployee(Model model, @ModelAttribute("employee") EmployeeDTO employee,
                                 @PathVariable("id") int id) {

        employee.setRole(Role.USER);
        employeeService.update(employeeDTOMapper.fromDtoMap(employee), id);
        List<Employee> list = employeeService.getEmployeesOnly("USER");
        model.addAttribute("list", list);
        return "redirect:/admin/home";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/admin/home";
    }
}
