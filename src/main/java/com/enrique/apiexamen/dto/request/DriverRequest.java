package com.enrique.apiexamen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DriverRequest {
    @NotBlank(message = "El nombre no debe estar vacío")
    private String name;

    @NotBlank(message = "El numero de licencia no debe estar vacío")
    private String licenseNumber;
}
