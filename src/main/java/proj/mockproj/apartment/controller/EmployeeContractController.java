package proj.mockproj.apartment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.mockproj.apartment.model.EmployeeContract;
import proj.mockproj.apartment.service.EmployeeContractService;

@RestController
@RequestMapping("/api/contracts")
public class EmployeeContractController {

    @Autowired
    private EmployeeContractService contractService;

    // Lấy tất cả hợp đồng chưa bị xóa
    @GetMapping
    public ResponseEntity<List<EmployeeContract>> getAllActiveContracts() {
        List<EmployeeContract> contracts = contractService.getAllActiveContracts();
        return ResponseEntity.ok(contracts);
    }

    // Lấy hợp đồng theo ID nhưng chưa bị xóa
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeContract> getActiveContractById(@PathVariable Long id) {
        Optional<EmployeeContract> contract = contractService.getActiveContractById(id);
        return contract.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Lấy tất cả hợp đồng đã bị xóa
    @GetMapping("/deleted")
    public ResponseEntity<List<EmployeeContract>> getAllDeletedContracts() {
        List<EmployeeContract> deletedContracts = contractService.getAllDeletedContracts();
        return ResponseEntity.ok(deletedContracts);
    }

    // Lấy hợp đồng đã bị xóa theo ID
    @GetMapping("/deleted/{id}")
    public ResponseEntity<EmployeeContract> getDeletedContractById(@PathVariable Long id) {
        Optional<EmployeeContract> contract = contractService.getDeletedContractById(id);
        return contract.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới hợp đồng
    @PostMapping
    public ResponseEntity<EmployeeContract> createContract(@RequestBody EmployeeContract contract) {
        EmployeeContract createdContract = contractService.createContract(contract);
        return ResponseEntity.ok(createdContract);
    }

    // Cập nhật hợp đồng
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeContract> updateContract(@PathVariable Long id, @RequestBody EmployeeContract contractDetails) {
        EmployeeContract updatedContract = contractService.updateContract(id, contractDetails);
        return updatedContract != null ? ResponseEntity.ok(updatedContract) : ResponseEntity.notFound().build();
    }

    // Xóa mềm hợp đồng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteContract(@PathVariable Long id) {
        contractService.softDeleteContract(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy hợp đồng theo Employee ID nhưng chưa bị xóa
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeContract>> getActiveContractsByEmployeeId(@PathVariable Long employeeId) {
        List<EmployeeContract> contracts = contractService.getActiveContractsByEmployeeId(employeeId);
        return ResponseEntity.ok(contracts);
    }

    // Lấy hợp đồng đã bị xóa theo Employee ID
    @GetMapping("/deleted/employee/{employeeId}")
    public ResponseEntity<List<EmployeeContract>> getDeletedContractsByEmployeeId(@PathVariable Long employeeId) {
        List<EmployeeContract> deletedContracts = contractService.getDeletedContractsByEmployeeId(employeeId);
        return ResponseEntity.ok(deletedContracts);
    }
}
