package com.example.demothi.employee;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // LIST + FIND ?q=...
    @GetMapping("/employees")
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Employee> list = service.search(q);
        model.addAttribute("q", q);
        model.addAttribute("employees", list); // <— dùng “employees” cho khớp template
        return "employees-list";
    }

    // VIEW
    @GetMapping("/employees/{id}")
    public String view(@PathVariable("id") String id, Model model, RedirectAttributes ra) {
        return service.findById(id)
                .map(emp -> { model.addAttribute("emp", emp); return "employee-view"; })
                .orElseGet(() -> {
                    ra.addFlashAttribute("errorMessage", "Employee not found");
                    return "redirect:/employees";
                });
    }

    // CREATE - form
    @GetMapping("/employees/add")
    public String showAddForm(Model model) {
        if (!model.containsAttribute("employee")) {
            model.addAttribute("employee", new Employee());
        }
        model.addAttribute("mode", "add");
        model.addAttribute("formAction", "/employees"); // <— POST create
        return "employee-form";
    }

    // CREATE - submit
    @PostMapping("/employees")
    public String create(@Valid @ModelAttribute("employee") Employee employee,
                         BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.employee", br);
            ra.addFlashAttribute("employee", employee);
            ra.addFlashAttribute("mode", "add");
            return "redirect:/employees/add";
        }
        service.save(employee);
        ra.addFlashAttribute("successMessage", "Employee Details has been saved");
        return "redirect:/employees";
    }

    // UPDATE - form
    @GetMapping("/employees/{id}/edit")
    public String showEditForm(@PathVariable("id") String id, Model model, RedirectAttributes ra) {
        return service.findById(id)
                .map(emp -> {
                    if (!model.containsAttribute("employee")) {
                        model.addAttribute("employee", emp);
                    }
                    model.addAttribute("mode", "edit");
                    model.addAttribute("formAction", "/employees/" + id + "/update"); // <— POST update
                    return "employee-form";
                }).orElseGet(() -> {
                    ra.addFlashAttribute("errorMessage", "Employee not found");
                    return "redirect:/employees";
                });
    }

    // UPDATE - submit
    @PostMapping("/employees/{id}/update")
    public String update(@PathVariable("id") String id,
                         @Valid @ModelAttribute("employee") Employee employee,
                         BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.employee", br);
            ra.addFlashAttribute("employee", employee);
            ra.addFlashAttribute("mode", "edit");
            return "redirect:/employees/" + id + "/edit";
        }
        employee.setEmpId(id); // giữ id theo path
        service.save(employee);
        ra.addFlashAttribute("successMessage", "Employee updated successfully");
        return "redirect:/employees";
    }

    // DELETE (POST)
    @PostMapping("/employees/{id}/delete")
    public String delete(@PathVariable("id") String id, RedirectAttributes ra) {
        service.deleteById(id);
        ra.addFlashAttribute("successMessage", "Employee deleted");
        return "redirect:/employees";
    }
}
