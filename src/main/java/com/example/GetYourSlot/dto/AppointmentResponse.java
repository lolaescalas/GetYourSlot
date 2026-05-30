package com.example.GetYourSlot.dto;

import com.example.GetYourSlot.model.AppointmentStatus;
import lombok.Data;
import java.time.LocalDateTime;

//what the API returns about an appointment
@Data
public class AppointmentResponse {
    private Long id;
    private String clientName;
    private String serviceName;
    private LocalDateTime dateTime;
    private AppointmentStatus status;
}