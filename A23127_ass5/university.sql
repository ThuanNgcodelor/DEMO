CREATE DATABASE university;
GO

USE university;
GO

CREATE TABLE courses (
    course_code VARCHAR(6) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    semester VARCHAR(20) NOT NULL
);
GO

INSERT INTO courses (course_code, title, credits, instructor, semester)
VALUES
('CSE101', 'Intro to Computer Science', 3, 'John Smith', 'Fall 2025'),
('MAT201', 'Calculus II', 4, 'Alice Johnson', 'Spring 2025');
GO
