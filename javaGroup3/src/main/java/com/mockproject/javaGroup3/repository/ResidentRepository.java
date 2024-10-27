package com.mockproject.javaGroup3.repository;

import com.mockproject.javaGroup3.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> { }
