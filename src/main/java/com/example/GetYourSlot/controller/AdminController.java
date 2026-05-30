package com.example.GetYourSlot.controller;

import com.example.GetYourSlot.dto.AppointmentResponse;
import com.example.GetYourSlot.dto.ServiceResponse;
import com.example.GetYourSlot.model.SlotService;
import com.example.GetYourSlot.service.AppointmentService;
import com.example.GetYourSlot.service.SlotServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AppointmentService appointmentService;
    private final SlotServiceService slotServiceService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAll() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @PutMapping("/appointments/{id}/confirm")
    public ResponseEntity<AppointmentResponse> confirm(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.confirm(id));
    }

    @PostMapping("/services")
    public ResponseEntity<ServiceResponse> createService(@RequestBody SlotService service) {
        return ResponseEntity.ok(slotServiceService.create(service));
    }
}