package com.example.GetYourSlot.repository;

import com.example.GetYourSlot.model.Appointment;
import com.example.GetYourSlot.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClientId(Long clientId);
    boolean existsByServiceIdAndDateTimeAndStatusNot(
            Long serviceId, LocalDateTime dateTime, AppointmentStatus status
    );
}