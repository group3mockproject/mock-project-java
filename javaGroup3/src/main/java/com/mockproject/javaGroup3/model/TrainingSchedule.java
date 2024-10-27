package com.mockproject.javaGroup3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "training_schedule")
public class TrainingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "training_date", nullable = false)
    private LocalDate trainingDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "training_topic", nullable = false)
    private String trainingTopic;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false) // Khóa ngoại cho Employee
    private Employee trainer;

    @Column(name = "format", nullable = false)
    private String format;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "status", nullable = false)
    private String status; // Thêm trường status

    // Constructors (optional, if you want to define any)
    public TrainingSchedule() {
    }

    public TrainingSchedule(LocalDate trainingDate, LocalTime startTime, LocalTime endTime, String trainingTopic, Employee trainer, String format, String location, String status) {
        this.trainingDate = trainingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainingTopic = trainingTopic;
        this.trainer = trainer; // Thay đổi để lấy từ Employee
        this.format = format;
        this.location = location;
        this.status = status; // Thêm trường status vào constructor
    }
}
