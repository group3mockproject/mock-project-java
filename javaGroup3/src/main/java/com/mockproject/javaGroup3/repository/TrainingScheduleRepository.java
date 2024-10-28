package com.mockproject.javaGroup3.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mockproject.javaGroup3.model.TrainingSchedule;

public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Long> {
	
}

