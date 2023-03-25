package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.EmployeeDTO;
import com.example.app_for_task.dto.EmployeeDTOMapper;
import com.example.app_for_task.model.employee.Employee;
import com.example.app_for_task.model.employee.Role;
import com.example.app_for_task.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {

    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDTOMapper;
    private List<Employee> list;
    private List<EmployeeDTO> listDto;

    @GetMapping("/api")
    public String hello() {
        return "index";
    }

    @GetMapping("/admin/home")
    public String adminHome( Model model) {
        List<Employee> list = employeeService.getEmployeesOnly("USER");
        model.addAttribute("list", list);

        return "admin-home";
    }

    @RequestMapping("/admin/createemployee")
    public String newEmployee(Model model, @ModelAttribute("employee") Employee employee) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);

        model.addAttribute("employee", employee);
        model.addAttribute("roles", roles);
        return "create-employee";
    }

    @RequestMapping(value = "/admin/saveempl", method = RequestMethod.GET)
    public String createEmployee(Model model, @ModelAttribute("employee") Employee employee) {
        if (employee.getPassword()==null) {
            employee.setPassword("12345");
        }
        employeeService.add(employee);

        List<Employee> list = employeeService.getEmployeesOnly("USER");
        model.addAttribute("list", list);
        return "admin-home";
    }

    @RequestMapping("/admin/{id}/editemployee")
    public String editEmployee(Model model, @PathVariable(name = "id") int id) {
        model.addAttribute("employee", employeeService.getById(id));
        return "edit-employee";
    }

    @PatchMapping("/admin/{id}")
    public String updateEmployee(Model model, @ModelAttribute("employee") Employee employee,
                                 @PathVariable("id") int id) {

        employee.setRole(Role.USER);
        employeeService.update(employee, id);
        List<Employee> list = employeeService.getEmployeesOnly("USER");
        model.addAttribute("list", list);
        return "redirect:/admin/home";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/admin/home";
    }




//    @GetMapping("/admin/home")
//    public String adminHome() {
//        return "admin-home";
//    }
    //    @PostConstruct
//    public void loadData() {
//        list = employeeService.getAllEmployees();
//
//    }

//    @GetMapping("/admin")
//    public String showEmployees(Model model) {
//        model.addAttribute("employees", list);
//
//        return "admin";
//    }

//    @GetMapping("/login")
//    public String getLoginPage() {
//        return null;
//    }


//    @PostMapping("/register")
//    public EmployeeDTO register( @RequestBody EmployeeDTO employeeDTO) {
//        Employee employee = employeeService.create(employeeDTOMapper.fromDtoMap(employeeDTO));
//
//        return employeeDTOMapper.inDtoMap(employee);
//    }
}
