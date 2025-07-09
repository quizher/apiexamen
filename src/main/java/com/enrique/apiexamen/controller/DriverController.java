package com.enrique.apiexamen.controller;

import com.enrique.apiexamen.dto.request.DriverRequest;
import com.enrique.apiexamen.dto.response.DriverResponse;
import com.enrique.apiexamen.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public DriverResponse create(@Valid @RequestBody DriverRequest request) {
        return driverService.create(request);
    }

    @GetMapping
    public List<DriverResponse> getActiveDrivers() {
        return driverService.getActiveDrivers();
    }
}
