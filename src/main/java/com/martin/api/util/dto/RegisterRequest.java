package com.martin.api.util.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "El username no puede ir vacio")
    @Size(min = 6, max = 30, message = "el username debe tener entre 6 a 30 caracteres")
    String username,
    @NotBlank(message = "El email no puede ir vacio")
    @Email(message = "El email debe tener un formato valido")
    String email,
    @NotBlank(message = "El password no puede ir vacio")
    @Size(min = 6, max = 60, message = "el password debe tener entre 6 a 60 caracteres")
    String password
) {

}
