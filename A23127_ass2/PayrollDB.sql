CREATE DATABASE PayrollDB;
USE PayrollDB;

CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(100) NOT NULL,
    Position NVARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Salary DECIMAL(18, 2) NOT NULL,
    HireDate DATE NOT NULL
);

INSERT INTO Employees (FullName, Position, DateOfBirth, Salary, HireDate)
VALUES 
(N'Nguyen Van A', N'Developer', '1995-05-10', 15000, '2020-01-01'),
(N'Nguyen Van B', N'HR', '1990-09-12', 12000, '2019-03-15'),
(N'Nguyen Van C', N'Manager', '1985-07-22', 25000, '2015-06-01'),
(N'Nguyen Van D', N'Accountant', '1992-02-28', 10000, '2021-07-01'),
(N'Nguyen Van E', N'Tester', '1998-11-05', 9000, '2022-05-10');
