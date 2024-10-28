package com.mockproject.javaGroup3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockproject.javaGroup3.model.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> { }
