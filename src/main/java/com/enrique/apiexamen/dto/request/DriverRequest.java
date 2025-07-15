package com.enrique.apiexamen.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para registrar un nuevo conductor")
public class DriverRequest {

    @NotBlank(message = "El nombre no debe estar vacío")
    @Schema(description = "Nombre completo del conductor", example = "Juan Pérez")
    private String name;

    @NotBlank(message = "El número de licencia no debe estar vacío")
    @Schema(description = "Número de licencia del conductor", example = "LIC123456789")
    private String licenseNumber;
}
