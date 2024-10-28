package com.mockproject.javaGroup3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.javaGroup3.model.MaintenanceRequests;


@Repository
public interface MaintenanceRequestsRepository extends JpaRepository<MaintenanceRequests, Long> {
}
