/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demothi.employee;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> findAll() { return repo.findAll(); }

    public Optional<Employee> findById(String id) { return repo.findById(id); }

    @Transactional
    public Employee save(Employee e) { return repo.save(e); }

    @Transactional
    public void deleteById(String id) { repo.deleteById(id); }

    public List<Employee> search(String q) {
        if (q == null || q.trim().isEmpty()) return repo.findAll();
        String s = q.trim();
        return repo.findByEmpIdContainingIgnoreCaseOrEmpNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(s, s, s);
    }
}