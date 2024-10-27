package com.mockproject.javaGroup3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.javaGroup3.model.EmployeeContract;
import com.mockproject.javaGroup3.service.EmployeeContractService;

@RestController
@RequestMapping("/api/v1/contracts")
public class EmployeeContractController {

    @Autowired
    private EmployeeContractService contractService;

    // Tạo mới hợp đồng
    @PostMapping
    public ResponseEntity<EmployeeContract> createContract(@RequestBody EmployeeContract contract) {
        EmployeeContract createdContract = contractService.createContract(contract);
        return ResponseEntity.ok(createdContract);
    }

    // Cập nhật hợp đồng
    @PutMapping("/{contractId}")
    public ResponseEntity<EmployeeContract> updateContract(
            @PathVariable Long contractId,
            @RequestBody EmployeeContract contractDetails) {
        EmployeeContract updatedContract = contractService.updateContract(contractId, contractDetails);
        return updatedContract != null 
            ? ResponseEntity.ok(updatedContract) 
            : ResponseEntity.notFound().build();
    }

    // Xóa mềm hợp đồng
    @DeleteMapping("/{contractId}")
    public ResponseEntity<Void> softDeleteContract(@PathVariable Long contractId) {
        contractService.softDeleteContract(contractId);
        return ResponseEntity.ok().build();
    }

    // Lấy tất cả hợp đồng chưa bị xóa
    @GetMapping("/active")
    public ResponseEntity<List<EmployeeContract>> getAllActiveContracts() {
        List<EmployeeContract> contracts = contractService.getAllActiveContracts();
        return ResponseEntity.ok(contracts);
    }

    // Lấy hợp đồng theo ID (chưa bị xóa)
    @GetMapping("/active/{contractId}")
    public ResponseEntity<EmployeeContract> getActiveContractById(@PathVariable Long contractId) {
        Optional<EmployeeContract> contract = contractService.getActiveContractById(contractId);
        return contract.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tìm kiếm hợp đồng theo tên nhân viên
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeContract>> searchEmployeeContracts(
            @RequestParam(value = "employeeName", required = false) String employeeName) {
        
        List<EmployeeContract> contracts = contractService.searchEmployeeContracts(employeeName);
        return ResponseEntity.ok(contracts);
    }

    // Lấy tất cả hợp đồng đã bị xóa
    @GetMapping("/deleted")
    public ResponseEntity<List<EmployeeContract>> getAllDeletedContracts() {
        List<EmployeeContract> contracts = contractService.getAllDeletedContracts();
        return ResponseEntity.ok(contracts);
    }

    // Lấy hợp đồng theo ID (đã bị xóa)
    @GetMapping("/deleted/{contractId}")
    public ResponseEntity<EmployeeContract> getDeletedContractById(@PathVariable Long contractId) {
        Optional<EmployeeContract> contract = contractService.getDeletedContractById(contractId);
        return contract.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
