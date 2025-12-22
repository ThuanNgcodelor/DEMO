package com.example.demothi.employee;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @NotBlank(message = "Emp ID is required")
    @Size(min = 4, max = 4, message = "Emp ID must be exactly 4 characters")
    private String empId;

    @NotBlank(message = "Emp Name is required")
    @Size(max = 100, message = "Emp Name too long")
    private String empName;

    @NotBlank(message = "Phone is required")
    @Size(max = 30, message = "Phone too long")
    private String phone;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "500.00", message = "Salary must be at least 500 USD")
    private BigDecimal salary;

    public Employee() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}