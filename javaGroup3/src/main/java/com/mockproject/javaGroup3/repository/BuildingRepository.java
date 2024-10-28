package com.mockproject.javaGroup3.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mockproject.javaGroup3.model.Building;


public interface BuildingRepository extends JpaRepository<Building, Long> {
}