package com.enrique.apiexamen.dto.response;

import com.enrique.apiexamen.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Respuesta detallada de una orden")
public class OrderResponse {

    @Schema(description = "ID único de la orden", example = "ab2d5cde-91bc-4e8a-8821-4e1f2f342a01")
    private UUID id;

    @Schema(description = "Ciudad de origen", example = "Ciudad de México")
    private String origin;

    @Schema(description = "Ciudad de destino", example = "Guadalajara")
    private String destination;

    @Schema(description = "Estado actual de la orden", example = "CREATED")
    private OrderStatus status;

    @Schema(description = "Fecha de creación de la orden", example = "2025-07-15T12:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "Última fecha de modificación", example = "2025-07-15T13:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "Conductor asignado a la orden")
    private DriverResponse driver;

    @Schema(description = "Ruta al archivo adjunto (PDF)", example = "uploads/ab2d5cde/orden.pdf")
    private String attachmentPath;

    @Schema(description = "Ruta a la imagen adjunta", example = "uploads/ab2d5cde/foto.jpg")
    private String imagePath;
}
