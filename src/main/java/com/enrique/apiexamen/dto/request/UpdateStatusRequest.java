package com.enrique.apiexamen.dto.request;

import com.enrique.apiexamen.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    @NotNull(message = "El estado es obligatorio")
    private OrderStatus status;
}
