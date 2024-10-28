package com.mockproject.javaGroup3.dto;

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
	public void setEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		
	}
}