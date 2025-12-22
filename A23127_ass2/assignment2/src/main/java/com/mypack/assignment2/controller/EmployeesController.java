package com.mypack.assignment2.controller;

import com.mypack.assignment2.entity.Employees;
import com.mypack.assignment2.repository.EmployeesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeesRepository employeesRepo;

    @GetMapping("/")
    public String index() {
        return "redirect:/employees";
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("list", employeesRepo.findAll());
        model.addAttribute("employee", new Employees());
        return "employees";
    }

    @PostMapping("/employees/save")
    public String save(@Valid @ModelAttribute("employee") Employees employee,
            BindingResult result,
            Model model) {
        if (employee.getDateOfBirth() != null) {
            int age = Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears();
            if (age < 18) {
                result.rejectValue("dateOfBirth", "error.employee", "Employee must be at least 18 years old");
            }
        }

        if (employee.getHireDate() != null && employee.getHireDate().isAfter(LocalDate.now())) {
            result.rejectValue("hireDate", "error.employee", "Hire date cannot be in the future");
        }

        if (result.hasErrors()) {
            model.addAttribute("list", employeesRepo.findAll());
            return "employees";
        }

        employeesRepo.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeesRepo.findById(id).orElse(null));
        model.addAttribute("list", employeesRepo.findAll());
        return "employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        employeesRepo.deleteById(id);
        return "redirect:/employees";
    }

    @PostMapping("/employees/search")
    public String search(@RequestParam(required = false) String fullName,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            Model model) {
        List<Employees> result;

        if (fullName != null && !fullName.isBlank()) {
            result = employeesRepo.findByFullNameContainingIgnoreCase(fullName);
        } else if (position != null && !position.isBlank()) {
            result = employeesRepo.findByPositionContainingIgnoreCase(position);
        } else if (minSalary != null && maxSalary != null) {
            result = employeesRepo.findBySalaryBetween(minSalary, maxSalary);
        } else {
            result = employeesRepo.findAll();
        }

        model.addAttribute("list", result);
        model.addAttribute("employee", new Employees());
        return "employees";
    }
}
