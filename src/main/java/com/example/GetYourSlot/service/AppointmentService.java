package com.example.GetYourSlot.service;

import com.example.GetYourSlot.dto.AppointmentRequest;
import com.example.GetYourSlot.dto.AppointmentResponse;
import com.example.GetYourSlot.model.Appointment;
import com.example.GetYourSlot.model.AppointmentStatus;
import com.example.GetYourSlot.repository.AppointmentRepository;
import com.example.GetYourSlot.repository.SlotServiceRepository;
import com.example.GetYourSlot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final SlotServiceRepository slotServiceRepository;

    public AppointmentResponse create(AppointmentRequest request, String clientEmail) {
        var client = userRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var service = slotServiceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        boolean slotTaken = appointmentRepository.existsByServiceIdAndDateTimeAndStatusNot(
                service.getId(), request.getDateTime(), AppointmentStatus.CANCELLED);

        if (slotTaken) throw new RuntimeException("This time slot is already taken");

        Appointment appointment = Appointment.builder()
                .client(client)
                .service(service)
                .dateTime(request.getDateTime())
                .status(AppointmentStatus.PENDING)
                .build();

        appointmentRepository.save(appointment);
        return toResponse(appointment);
    }

    public List<AppointmentResponse> getMyAppointments(String clientEmail) {
        var client = userRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return appointmentRepository.findByClientId(client.getId())
                .stream().map(this::toResponse).toList();
    }

    public AppointmentResponse cancel(Long id, String clientEmail) {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getClient().getEmail().equals(clientEmail))
            throw new RuntimeException("Not authorized");

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
        return toResponse(appointment);
    }

    public List<AppointmentResponse> getAll() {
        return appointmentRepository.findAll()
                .stream().map(this::toResponse).toList();
    }

    public AppointmentResponse confirm(Long id) {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointmentRepository.save(appointment);
        return toResponse(appointment);
    }

    private AppointmentResponse toResponse(Appointment a) {
        AppointmentResponse r = new AppointmentResponse();
        r.setId(a.getId());
        r.setClientName(a.getClient().getName());
        r.setServiceName(a.getService().getName());
        r.setDateTime(a.getDateTime());
        r.setStatus(a.getStatus());
        return r;
    }
}