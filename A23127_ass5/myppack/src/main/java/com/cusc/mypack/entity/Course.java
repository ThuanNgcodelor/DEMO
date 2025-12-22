package com.cusc.mypack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "courses") 
public class Course {

    @Id
    @NotBlank(message = "Course code is required")
    @Pattern(regexp = "^[A-Za-z]{3}[0-9]{3}$", message = "Course code must be 3 letters followed by 3 numbers")
    private String courseCode;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title can have at most 100 characters")
    private String title;

    @Min(value = 1, message = "Credits must be between 1 and 5")
    @Max(value = 5, message = "Credits must be between 1 and 5")
    private int credits;

    @NotBlank(message = "Instructor is required")
    @Size(max = 100, message = "Instructor name can have at most 100 characters")
    private String instructor;

    @NotBlank(message = "Semester is required")
    @Pattern(regexp = "^(Spring|Summer|Fall)\\s?\\d{4}$", message = "Semester must be Spring|Summer|Fall + 4-digit year")
    private String semester;

    // Constructors
    public Course() {}

    public Course(String courseCode, String title, int credits, String instructor, String semester) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
        this.semester = semester;
    }

    // Getters & Setters
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
