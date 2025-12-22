package com.mypack.controller;

import com.mypack.models.Registrant;
import com.mypack.repo.InMemoryRegistrantRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class RegistrantController {

    private final InMemoryRegistrantRepo repo;

    public RegistrantController(InMemoryRegistrantRepo repo) {
        this.repo = repo;
    }

    @ModelAttribute("workshopTitles")
    public List<String> titles() {
        return Arrays.asList("AI in Practice", "Cloud Computing", "Cybersecurity Basics");
    }

    @GetMapping({"/", "/register"})
    public String showForm(Model model) {
        model.addAttribute("registrant", new Registrant());
        model.addAttribute("view", "register");
        return "app";
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("registrant") Registrant registrant,
                             BindingResult br,
                             Model model) {
        if (br.hasErrors()) {
            model.addAttribute("view", "register");
            return "app";
        }
        Registrant saved = repo.add(registrant);
        model.addAttribute("saved", saved);
        model.addAttribute("view", "confirm");
        return "app";
    }

    @GetMapping("/registrants")
    public String list(Model model,
                       @ModelAttribute("msg") String msg) {
        model.addAttribute("items", repo.findAll());
        model.addAttribute("view", "list");
        return "app";
    }

    @GetMapping("/registrants/edit/{id}")
    public String editForm(@PathVariable("id") Long id,  
                           Model model,
                           RedirectAttributes ra) {
        return repo.findById(id).map(r -> {
            model.addAttribute("registrant", r);
            model.addAttribute("view", "edit");
            return "app";
        }).orElseGet(() -> {
            ra.addFlashAttribute("msg", "Registrant not found");
            return "redirect:/registrants";
        });
    }

    @PostMapping("/registrants/edit/{id}")
    public String doEdit(@PathVariable("id") Long id,  
                         @Valid @ModelAttribute("registrant") Registrant registrant,
                         BindingResult br,
                         RedirectAttributes ra,
                         Model model) {
        if (br.hasErrors()) {
            model.addAttribute("view", "edit");
            return "app";
        }
        boolean ok = repo.update(id, registrant);
        ra.addFlashAttribute("msg", ok ? "Registrant information updated"
                                       : "Registrant not found");
        return "redirect:/registrants";
    }

    @PostMapping("/registrants/delete/{id}")
    public String delete(@PathVariable("id") Long id, 
                         RedirectAttributes ra) {
        boolean ok = repo.delete(id);
        ra.addFlashAttribute("msg", ok ? "Registrant removed from the list"
                                       : "Registrant not found");
        return "redirect:/registrants";
    }
}
