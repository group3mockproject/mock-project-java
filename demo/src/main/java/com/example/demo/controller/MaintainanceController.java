package com.example.demo.controller;

import com.example.demo.dto.MaintenanceRequestDTO;
import com.example.demo.service.MaintenanceRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
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