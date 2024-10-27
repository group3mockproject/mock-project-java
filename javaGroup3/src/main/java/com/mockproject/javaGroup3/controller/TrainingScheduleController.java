package com.mockproject.javaGroup3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.javaGroup3.model.TrainingSchedule;
import com.mockproject.javaGroup3.service.TrainingScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/training-schedules")
public class TrainingScheduleController {

    @Autowired
    private TrainingScheduleService trainingScheduleService;

    // Lấy tất cả lịch đào tạo
    @GetMapping
    public ResponseEntity<List<TrainingSchedule>> getAllTrainingSchedules() {
        List<TrainingSchedule> schedules = trainingScheduleService.getAllTrainingSchedules();
        return ResponseEntity.ok(schedules);
    }

    // Lấy một lịch đào tạo theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainingSchedule> getTrainingScheduleById(@PathVariable Long id) {
        TrainingSchedule schedule = trainingScheduleService.getTrainingScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    // Tạo mới lịch đào tạo
    @PostMapping
    public ResponseEntity<TrainingSchedule> createTrainingSchedule(@RequestBody TrainingSchedule trainingSchedule) {
        TrainingSchedule createdSchedule = trainingScheduleService.createTrainingSchedule(trainingSchedule);
        return ResponseEntity.status(201).body(createdSchedule);
    }

    // Cập nhật lịch đào tạo
    @PutMapping("/{id}")
    public ResponseEntity<TrainingSchedule> updateTrainingSchedule(@PathVariable Long id, @RequestBody TrainingSchedule trainingSchedule) {
        TrainingSchedule updatedSchedule = trainingScheduleService.updateTrainingSchedule(id, trainingSchedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    // Xóa lịch đào tạo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingSchedule(@PathVariable Long id) {
        trainingScheduleService.deleteTrainingSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
