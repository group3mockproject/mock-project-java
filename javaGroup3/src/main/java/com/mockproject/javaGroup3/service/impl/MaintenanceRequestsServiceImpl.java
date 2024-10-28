package com.mockproject.javaGroup3.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mockproject.javaGroup3.dto.MaintenanceRequestDTO;
import com.mockproject.javaGroup3.model.MaintenanceRequests;
import com.mockproject.javaGroup3.repository.EmployeeRepository;
import com.mockproject.javaGroup3.repository.MaintenanceRequestsRepository;
import com.mockproject.javaGroup3.repository.ResidentRepository;
import com.mockproject.javaGroup3.service.MaintenanceRequestsService;

@Service
public class MaintenanceRequestsServiceImpl implements MaintenanceRequestsService {

    @Autowired
    private MaintenanceRequestsRepository maintenanceRequestsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceRequestDTO> getAllRequests() {
        return maintenanceRequestsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MaintenanceRequestDTO getRequestById(Long id) {
        Optional<MaintenanceRequests> request = maintenanceRequestsRepository.findById(id);
        return request.map(this::convertToDTO).orElse(null);
    }

    @Override
    @Transactional
    public MaintenanceRequestDTO createRequest(MaintenanceRequestDTO requestDTO) {
        MaintenanceRequests request = convertToEntity(requestDTO);
        MaintenanceRequests savedRequest = maintenanceRequestsRepository.save(request);
        return convertToDTO(savedRequest);
    }

    @Override
    @Transactional
    public MaintenanceRequestDTO updateRequest(Long id, MaintenanceRequestDTO requestDTO) {
        Optional<MaintenanceRequests> optionalRequest = maintenanceRequestsRepository.findById(id);
        if (optionalRequest.isPresent()) {
            MaintenanceRequests request = optionalRequest.get();
            request.setResident(residentRepository.findById(requestDTO.getResidentId()).orElse(null));
            request.setEmployee(employeeRepository.findById(requestDTO.getEmployeeId()).orElse(null));
            request.setRequestDate(requestDTO.getRequestDate());
            request.setStatus(requestDTO.getStatus());
            request.setDescription(requestDTO.getDescription());
            request.setDelFlag(requestDTO.getDelFlag());
            MaintenanceRequests updatedRequest = maintenanceRequestsRepository.save(request);
            return convertToDTO(updatedRequest);
        } else {
            return null;	
        }
    }

    @Override
    @Transactional
    public boolean deleteRequest(Long id) {
        if (maintenanceRequestsRepository.existsById(id)) {
            maintenanceRequestsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private MaintenanceRequestDTO convertToDTO(MaintenanceRequests request) {
        MaintenanceRequestDTO dto = new MaintenanceRequestDTO();
        dto.setRequestId(request.getRequestId()); // Add this line
        if (request.getResident() != null) {
            dto.setResidentId(request.getResident().getResidentId());
        }
        if (request.getEmployee() != null) {
            dto.setEmployeeId(request.getEmployee().getEmployeeId());
        }
        dto.setRequestDate(request.getRequestDate());
        dto.setStatus(request.getStatus());
        dto.setDescription(request.getDescription());
        dto.setDelFlag(request.getDelFlag());
        return dto;
    }

    private MaintenanceRequests convertToEntity(MaintenanceRequestDTO dto) {
        MaintenanceRequests request = new MaintenanceRequests();
        request.setResident(residentRepository.findById(dto.getResidentId()).orElse(null));
        request.setEmployee(employeeRepository.findById(dto.getEmployeeId()).orElse(null));
        request.setRequestDate(dto.getRequestDate());
        request.setStatus(dto.getStatus());
        request.setDescription(dto.getDescription());
        request.setDelFlag(dto.getDelFlag());
        return request;
    }
}