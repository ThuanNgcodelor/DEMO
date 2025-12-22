package com.mypack.assignment2.repository;

import com.mypack.assignment2.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    List<Employees> findByFullNameContainingIgnoreCase(String fullName);
    List<Employees> findByPositionContainingIgnoreCase(String position);
    List<Employees> findBySalaryBetween(Double min, Double max);
}
