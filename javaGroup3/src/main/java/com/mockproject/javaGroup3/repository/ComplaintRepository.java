package com.mockproject.javaGroup3.repository;

import com.mockproject.javaGroup3.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> { }
