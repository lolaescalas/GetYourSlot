package com.example.GetYourSlot.controller;

import com.example.GetYourSlot.dto.AppointmentRequest;
import com.example.GetYourSlot.dto.AppointmentResponse;
import com.example.GetYourSlot.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(
            @RequestBody AppointmentRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(appointmentService.create(request, email));
    }

    @GetMapping("/my")
    public ResponseEntity<List<AppointmentResponse>> myAppointments(
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(appointmentService.getMyAppointments(email));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancel(
            @PathVariable Long id,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(appointmentService.cancel(id, email));
    }
}