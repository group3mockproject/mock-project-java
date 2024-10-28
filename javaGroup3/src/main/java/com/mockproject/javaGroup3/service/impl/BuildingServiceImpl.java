package com.mockproject.javaGroup3.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mockproject.javaGroup3.dto.BuildingDTO;
import com.mockproject.javaGroup3.model.Building;
import com.mockproject.javaGroup3.repository.BuildingRepository;
import com.mockproject.javaGroup3.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BuildingDTO> getAllBuildings() {
        return buildingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BuildingDTO getBuildingById(Long id) {
        Optional<Building> building = buildingRepository.findById(id);
        return building.map(this::convertToDTO).orElse(null);
    }

    @Override
    @Transactional
    public BuildingDTO createBuilding(BuildingDTO buildingDTO) {
        Building building = convertToEntity(buildingDTO);
        Building savedBuilding = buildingRepository.save(building);
        return convertToDTO(savedBuilding);
    }

    @Override
    @Transactional
    public BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO) {
        Optional<Building> optionalBuilding = buildingRepository.findById(id);
        if (optionalBuilding.isPresent()) {
            Building building = optionalBuilding.get();
            building.setName(buildingDTO.getName());
            building.setLocation(buildingDTO.getLocation());
            building.setNumOfFloor(buildingDTO.getNumOfFloors());

            building.setDelFlag(buildingDTO.getDelFlag());
            Building updatedBuilding = buildingRepository.save(building);
            return convertToDTO(updatedBuilding);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteBuilding(Long id) {
        if (buildingRepository.existsById(id)) {
            buildingRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    private BuildingDTO convertToDTO(Building building) {
        BuildingDTO dto = new BuildingDTO();
        dto.setBuildingId(building.getBuildingId());
        dto.setName(building.getName());
        dto.setLocation(building.getLocation());
        dto.setNumOfFloors(building.getNumOfFloor());
        dto.setConstructionYear(building.getConstructionYear());
        dto.setDelFlag(building.getDelFlag());
        return dto;
    }

    private Building convertToEntity(BuildingDTO dto) {
        Building building = new Building();
        building.setName(dto.getName());
        building.setLocation(dto.getLocation());
        building.setNumOfFloor(dto.getNumOfFloors());
        building.setConstructionYear(dto.getConstructionYear());
        building.setDelFlag(dto.getDelFlag());
        return building;
    }
}