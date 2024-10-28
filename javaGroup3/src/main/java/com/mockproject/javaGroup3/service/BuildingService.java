package com.mockproject.javaGroup3.service;

import java.util.List;

import com.mockproject.javaGroup3.dto.BuildingDTO;

public interface BuildingService {
    List<BuildingDTO> getAllBuildings();

    BuildingDTO getBuildingById(Long id);

    BuildingDTO createBuilding(BuildingDTO buildingDTO);

    BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO);

    boolean deleteBuilding(Long id);
}
