package com.example.GetYourSlot.dto;

import lombok.Data;

//what the API returns about a service
@Data
public class ServiceResponse {
    private Long id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private Double price;

}