package com.enrique.apiexamen.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class DriverResponse {
    private UUID id;
    private String name;
    private String licenseNumber;
    private boolean active;
}
