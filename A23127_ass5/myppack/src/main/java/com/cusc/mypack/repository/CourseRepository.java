package com.cusc.mypack.repository;

import com.cusc.mypack.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
