package com.example.demo.service;

import com.example.demo.dto.BuildingDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> getAllBuildings();

    BuildingDTO getBuildingById(Long id);

    BuildingDTO createBuilding(BuildingDTO buildingDTO);

    BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO);

    boolean deleteBuilding(Long id);
}