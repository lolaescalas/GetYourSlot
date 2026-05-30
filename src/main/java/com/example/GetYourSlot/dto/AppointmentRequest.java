package com.example.GetYourSlot.dto;

import lombok.Data;
import java.time.LocalDateTime;

//what the client sends to create an appointment
@Data
public class AppointmentRequest {
    private Long serviceId;
    private LocalDateTime dateTime;
}