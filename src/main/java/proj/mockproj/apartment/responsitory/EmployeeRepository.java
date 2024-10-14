package proj.mockproj.apartment.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.mockproj.apartment.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Bạn có thể thêm các phương thức tùy chỉnh tại đây nếu cần
}

