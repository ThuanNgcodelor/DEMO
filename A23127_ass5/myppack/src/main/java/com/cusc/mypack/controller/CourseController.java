package com.cusc.mypack.controller;

import com.cusc.mypack.entity.Course;
import com.cusc.mypack.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("list", courseRepo.findAll());
        model.addAttribute("course", new Course());
        return "courses";
    }

    @PostMapping("/courses/save")
    public String save(@Valid @ModelAttribute("course") Course course,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("list", courseRepo.findAll());
            return "courses";
        }
        courseRepo.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{code}")
    public String edit(@PathVariable("code") String code, Model model) {
        model.addAttribute("course", courseRepo.findById(code).orElse(null));
        model.addAttribute("list", courseRepo.findAll());
        return "courses";
    }

    @GetMapping("/courses/delete/{code}")
    public String delete(@PathVariable("code") String code) {
        courseRepo.deleteById(code);
        return "redirect:/courses";
    }
}
