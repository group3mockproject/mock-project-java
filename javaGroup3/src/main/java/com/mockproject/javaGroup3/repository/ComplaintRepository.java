package com.mockproject.javaGroup3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockproject.javaGroup3.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> { }
