package com.enrique.apiexamen.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Información de un conductor")
public class DriverResponse {

    @Schema(description = "ID único del conductor", example = "d37c9c1a-94f3-4a62-9c58-123456789abc")
    private UUID id;

    @Schema(description = "Nombre completo del conductor", example = "Juan Pérez")
    private String name;

    @Schema(description = "Número de licencia del conductor", example = "LIC123456789")
    private String licenseNumber;

    @Schema(description = "Indica si el conductor está activo", example = "true")
    private boolean active;
}
