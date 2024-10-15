package proj.mockproj.apartment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.mockproj.apartment.model.EmployeeContract;
import proj.mockproj.apartment.responsitory.EmployeeContractRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeContractService {

    @Autowired
    private EmployeeContractRepository contractRepository;

    // Tạo mới hợp đồng
    public EmployeeContract createContract(EmployeeContract contract) {
        return contractRepository.save(contract);
    }

    // Cập nhật hợp đồng
    public EmployeeContract updateContract(Long contractId, EmployeeContract contractDetails) {
        return contractRepository.findById(contractId)
                .map(contract -> {
                    contract.setPosition(contractDetails.getPosition());
                    contract.setBenefits(contractDetails.getBenefits());
                    contract.setTerms(contractDetails.getTerms());
                    // Cập nhật các trường khác nếu có
                    return contractRepository.save(contract);
                }).orElse(null);
    }

    // Xóa mềm hợp đồng
    public void softDeleteContract(Long contractId) {
        contractRepository.softDelete(contractId);
    }

    // Các phương thức lấy hợp đồng chưa bị xóa
    public List<EmployeeContract> getAllActiveContracts() {
        return contractRepository.findAllByDeletedFalse();
    }

    public Optional<EmployeeContract> getActiveContractById(Long contractId) {
        return contractRepository.findByIdAndDeletedFalse(contractId);
    }

    public List<EmployeeContract> getActiveContractsByEmployeeId(Long employeeId) {
        return contractRepository.findByEmployee_IdAndDeletedFalse(employeeId);
    }

    // Các phương thức lấy hợp đồng đã bị xóa
    public List<EmployeeContract> getAllDeletedContracts() {
        return contractRepository.findAllByDeletedTrue();
    }

    public Optional<EmployeeContract> getDeletedContractById(Long contractId) {
        return contractRepository.findByIdAndDeletedTrue(contractId);
    }

    public List<EmployeeContract> getDeletedContractsByEmployeeId(Long employeeId) {
        return contractRepository.findByEmployee_IdAndDeletedTrue(employeeId);
    }
}
