package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MaintenanceRequestDTO {
    private Long requestId;
    private Long residentId;
    private Long employeeId;
    private Date requestDate;
    private String status;
    private String description;
    private Boolean delFlag;
}
