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

import com.mockproject.javaGroup3.dto.BuildingDTO;
import com.mockproject.javaGroup3.service.BuildingService;

@RestController
@RequestMapping("api/v1/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public List<BuildingDTO> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable Long id) {
        BuildingDTO building = buildingService.getBuildingById(id);
        if (building != null) {
            return ResponseEntity.ok(building);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody BuildingDTO buildingDTO) {
        return buildingService.createBuilding(buildingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@PathVariable Long id, @RequestBody BuildingDTO buildingDTO) {
        BuildingDTO updatedBuilding = buildingService.updateBuilding(id, buildingDTO);
        if (updatedBuilding != null) {
            return ResponseEntity.ok(updatedBuilding);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        boolean isDeleted = buildingService.deleteBuilding(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
