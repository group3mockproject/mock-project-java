package com.mockproject.javaGroup3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.javaGroup3.model.Employee;
import com.mockproject.javaGroup3.respository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Lưu employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Tìm tất cả employees
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    // Tìm employee theo ID
    public Optional<Employee> findEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    // Cập nhật thông tin employee
    public Optional<Employee> updateEmployee(Integer id, Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstname(employeeDetails.getFirstname());
            employee.setLastname(employeeDetails.getLastname());
            employee.setDob(employeeDetails.getDob());
            employee.setPosition(employeeDetails.getPosition());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhone(employeeDetails.getPhone());
            employee.setSsn(employeeDetails.getSsn());
            employee.setStatus(employeeDetails.getStatus());
            employee.setPassword(employeeDetails.getPassword());
            employee.setDelflag(employeeDetails.getDelflag());
            return employeeRepository.save(employee);
        });
    }

    // Xóa employee theo ID
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}


