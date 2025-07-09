package com.enrique.apiexamen.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AssignmentRequest {
    @NotNull(message = "El ID del conductor es obligatorio")
    private UUID driverId;
}
