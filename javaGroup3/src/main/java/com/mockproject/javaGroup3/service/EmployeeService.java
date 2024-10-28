package com.mockproject.javaGroup3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.javaGroup3.model.Employee;
import com.mockproject.javaGroup3.repository.EmployeeRepository;

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

    // Tìm employee theo employeeId
    public Optional<Employee> findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    // Cập nhật thông tin employee
    public Optional<Employee> updateEmployee(Long employeeId, Employee employeeDetails) {
        return employeeRepository.findById(employeeId).map(employee -> {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setDob(employeeDetails.getDob());
            employee.setPosition(employeeDetails.getPosition());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhone(employeeDetails.getPhone());
            employee.setSsn(employeeDetails.getSsn());
            employee.setStatus(employeeDetails.getStatus());
            employee.setPassword(employeeDetails.getPassword());
            employee.setDelFlag(employeeDetails.getDelFlag());
            return employeeRepository.save(employee);
        });
    }

    // Xóa employee theo employeeId
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
