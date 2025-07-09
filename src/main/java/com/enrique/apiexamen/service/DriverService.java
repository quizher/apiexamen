package com.enrique.apiexamen.service;

import com.enrique.apiexamen.dto.request.DriverRequest;
import com.enrique.apiexamen.dto.response.DriverResponse;
import com.enrique.apiexamen.entity.Driver;
import com.enrique.apiexamen.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;

    public DriverResponse create(DriverRequest request) {
        Driver driver = Driver.builder()
                .name(request.getName())
                .licenseNumber(request.getLicenseNumber())
                .active(true)
                .build();

        return toResponse(repository.save(driver));
    }

    public List<DriverResponse> getActiveDrivers() {
        return repository.findByActiveTrue().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private DriverResponse toResponse(Driver driver) {
        DriverResponse dto = new DriverResponse();
        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setLicenseNumber(driver.getLicenseNumber());
        return dto;
    }
}
