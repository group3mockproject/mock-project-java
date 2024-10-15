package com.mockproject.javaGroup3.repository;

import com.mockproject.javaGroup3.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> { }
