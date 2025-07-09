package com.enrique.apiexamen.dto.response;

import com.enrique.apiexamen.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderResponse {
    private UUID id;
    private String origin;
    private String destination;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DriverResponse driver;
    private String attachmentPath;
    private String imagePath;
}
