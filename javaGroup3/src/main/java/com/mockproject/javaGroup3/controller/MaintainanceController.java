package com.mockproject.javaGroup3.controller;

import java.util.List;

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

import com.mockproject.javaGroup3.dto.MaintenanceRequestDTO;
import com.mockproject.javaGroup3.service.MaintenanceRequestsService;

@RestController
@RequestMapping("api/v1/maintenance")
public class MaintainanceController {

    @Autowired
    private MaintenanceRequestsService maintenanceRequestsService;

    @GetMapping("/requests")
    public List<MaintenanceRequestDTO> getAllRequests() {
        return maintenanceRequestsService.getAllRequests();
    }

    @GetMapping("/requests/{id}")
    public ResponseEntity<MaintenanceRequestDTO> getRequestById(@PathVariable Long id) {
        MaintenanceRequestDTO request = maintenanceRequestsService.getRequestById(id);
        if (request != null) {
            return ResponseEntity.ok(request);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/requests")
    public MaintenanceRequestDTO createMaintenanceRequest(@RequestBody MaintenanceRequestDTO requestDTO) {
        return maintenanceRequestsService.createRequest(requestDTO);
    }

    @PutMapping("/requests/{id}")
    public ResponseEntity<MaintenanceRequestDTO> updateRequest(@PathVariable Long id, @RequestBody MaintenanceRequestDTO requestDTO) {
        MaintenanceRequestDTO updatedRequest = maintenanceRequestsService.updateRequest(id, requestDTO);
        if (updatedRequest != null) {
            return ResponseEntity.ok(updatedRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        boolean isDeleted = maintenanceRequestsService.deleteRequest(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
