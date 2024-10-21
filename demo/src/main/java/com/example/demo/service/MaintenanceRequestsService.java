package com.example.demo.service;

import com.example.demo.dto.MaintenanceRequestDTO;

import java.util.List;

public interface MaintenanceRequestsService {
    List<MaintenanceRequestDTO> getAllRequests();

    MaintenanceRequestDTO getRequestById(Long id);

    MaintenanceRequestDTO createRequest(MaintenanceRequestDTO requestDTO);

    MaintenanceRequestDTO updateRequest(Long id, MaintenanceRequestDTO requestDTO);

    boolean deleteRequest(Long id);
}