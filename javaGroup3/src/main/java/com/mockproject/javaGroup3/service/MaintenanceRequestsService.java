package com.mockproject.javaGroup3.service;


import java.util.List;

import com.mockproject.javaGroup3.dto.MaintenanceRequestDTO;

public interface MaintenanceRequestsService {
    List<MaintenanceRequestDTO> getAllRequests();

    MaintenanceRequestDTO getRequestById(Long id);

    MaintenanceRequestDTO createRequest(MaintenanceRequestDTO requestDTO);

    MaintenanceRequestDTO updateRequest(Long id, MaintenanceRequestDTO requestDTO);

    boolean deleteRequest(Long id);
}