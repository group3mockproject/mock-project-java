package com.example.demo.dto;

import lombok.Data;

@Data
public class BuildingDTO {
    private Long buildingId;
    private String name;
    private String location;
    private Integer numOfFloors;
    private Integer constructionYear;
    private Boolean delFlag;
}