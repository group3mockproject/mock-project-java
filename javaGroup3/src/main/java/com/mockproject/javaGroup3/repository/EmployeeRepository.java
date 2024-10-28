package com.mockproject.javaGroup3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.javaGroup3.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh tại đây nếu cần
}

