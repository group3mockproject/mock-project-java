package proj.mockproj.apartment.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj.mockproj.apartment.model.EmployeeContract;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeContractRepository extends JpaRepository<EmployeeContract, Long> {

    // Các phương thức tìm kiếm cho hợp đồng chưa bị xóa
    List<EmployeeContract> findAllByDeletedFalse();

    Optional<EmployeeContract> findByIdAndDeletedFalse(Long contractId);

    List<EmployeeContract> findByEmployee_IdAndDeletedFalse(Long employeeId);


    // Các phương thức tìm kiếm cho hợp đồng đã bị xóa
    List<EmployeeContract> findAllByDeletedTrue();

    Optional<EmployeeContract> findByIdAndDeletedTrue(Long contractId);

    List<EmployeeContract> findByEmployee_IdAndDeletedTrue(Long employeeId);

    // Phương thức để xóa mềm hợp đồng
    default void softDelete(Long contractId) {
        findById(contractId).ifPresent(contract -> {
            contract.setDeleted(true); // Đánh dấu là đã xóa
            save(contract); // Lưu lại hợp đồng đã thay đổi
        });
    }
}
