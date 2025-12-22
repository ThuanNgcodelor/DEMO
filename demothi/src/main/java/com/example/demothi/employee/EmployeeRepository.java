package com.example.demothi.employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByEmpIdContainingIgnoreCaseOrEmpNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(
            String id, String name, String phone);
}