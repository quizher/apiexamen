package com.enrique.apiexamen.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para crear una nueva orden")
public class OrderRequest {

    @NotBlank(message = "El origen no debe estar vacío")
    @Schema(description = "Ciudad o lugar de origen de la orden", example = "Ciudad de México")
    private String origin;

    @NotBlank(message = "El destino no debe estar vacío")
    @Schema(description = "Ciudad o lugar de destino de la orden", example = "Guadalajara")
    private String destination;
}
