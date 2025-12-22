package com.mypack.controller;


import com.mypack.dao.ShipperDAOImpl;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.mypack.entity.Shippers;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/shippers")
public class ShipperController {
    @Autowired
    private ShipperDAOImpl shipperDAO;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", new Shippers()); 
        model.addAttribute("shippers", shipperDAO.findAll());
        
        return "shipper";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("shipper") Shippers shipper,
                       BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("shippers", shipperDAO.findAll());
            return "shipper";
        }
        if (shipper.getShipperID() == null) {
            shipperDAO.insert(shipper);
        } else {
            shipperDAO.update(shipper);
        }
        return "redirect:/shippers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("shipper", shipperDAO.findById(id));
        model.addAttribute("shippers", shipperDAO.findAll());
        return "shipper";
    }

}
