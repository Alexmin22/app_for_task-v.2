package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.EmployeeDTO;
import com.example.app_for_task.dto.EmployeeDTOMapper;
//import com.example.app_for_task.dto.TaskDTO;
//import com.example.app_for_task.dto.TaskDTOMapper;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @PostMapping("/admin/home")
    public String createEmployee(Model model, @ModelAttribute("employee") EmployeeDTO employee) {

        Employee employee1 = employeeDTOMapper.fromDtoMap(employee);

        if (employee1.getPassword()==null) {
            employee1.setPassword("12345");
        }

        employeeService.add(employee1);

        List<Employee> listEmp = employeeService.getEmployeesOnly("USER");
        List<EmployeeDTO> list = employeeDTOMapper.inDtoMapList(listEmp);
        model.addAttribute("list", list);

        return "redirect:/admin/home";
    }

    @PatchMapping("/admin/{id}")
    public String updateEmployee(Model model, @ModelAttribute("employee") EmployeeDTO employee,
                                 @PathVariable("id") int id) {

        employee.setRole(Role.USER);
        employeeService.update(employeeDTOMapper.fromDtoMap(employee), id);

        List<Employee> listEmp = employeeService.getEmployeesOnly("USER");
        List<EmployeeDTO> list = employeeDTOMapper.inDtoMapList(listEmp);
        model.addAttribute("list", list);

        return "redirect:/admin/home";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/admin/home";
    }
}
