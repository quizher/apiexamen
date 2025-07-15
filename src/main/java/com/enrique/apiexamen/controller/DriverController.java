package com.enrique.apiexamen.controller;

import com.enrique.apiexamen.dto.request.DriverRequest;
import com.enrique.apiexamen.dto.response.DriverResponse;
import com.enrique.apiexamen.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Tag(name = "driver-controller", description = "Operaciones relacionadas con conductores")
public class DriverController {

    private final DriverService driverService;

    @Operation(summary = "Crear un nuevo conductor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conductor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public DriverResponse create(@Valid @RequestBody DriverRequest request) {
        return driverService.create(request);
    }

    @Operation(summary = "Obtener lista de conductores activos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<DriverResponse> getActiveDrivers() {
        return driverService.getActiveDrivers();
    }
}
