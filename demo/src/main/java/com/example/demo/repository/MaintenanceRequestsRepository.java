package com.example.demo.repository;

import com.example.demo.entities.MaintenanceRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRequestsRepository extends JpaRepository<MaintenanceRequests, Long> {
}