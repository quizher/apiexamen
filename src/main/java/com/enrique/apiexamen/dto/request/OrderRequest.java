package com.enrique.apiexamen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequest {
    @NotBlank(message = "El origen no debe estar vacío")
    private String origin;

    @NotBlank(message = "El destino no debe estar vacío")
    private String destination;
}
