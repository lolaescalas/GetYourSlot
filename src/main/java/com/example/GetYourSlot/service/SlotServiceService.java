package com.example.GetYourSlot.service;

import com.example.GetYourSlot.dto.ServiceResponse;
import com.example.GetYourSlot.repository.SlotServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceService {

    private final SlotServiceRepository serviceRepository;

    public List<ServiceResponse> getAll() {
        return serviceRepository.findAll().stream()
                .map(s -> {
                    ServiceResponse r = new ServiceResponse();
                    r.setId(s.getId());
                    r.setName(s.getName());
                    r.setDescription(s.getDescription());
                    r.setDurationMinutes(s.getDurationMinutes());
                    r.setPrice(s.getPrice());
                    return r;
                }).toList();
    }

    public ServiceResponse getById(Long id) {
        return serviceRepository.findById(id)
                .map(s -> {
                    ServiceResponse r = new ServiceResponse();
                    r.setId(s.getId());
                    r.setName(s.getName());
                    r.setDescription(s.getDescription());
                    r.setDurationMinutes(s.getDurationMinutes());
                    r.setPrice(s.getPrice());
                    return r;
                }).orElseThrow(() -> new RuntimeException("Service not found"));
    }
}