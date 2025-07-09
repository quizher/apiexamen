package com.enrique.apiexamen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "El usuario no debe estar vacío")
    private String username;

    @NotBlank(message = "La contraseña no debe estar vacía")
    private String password;
}

