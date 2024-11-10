package com.martin.api.util.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "El username no puede ir vacio")
    String username,
    @NotBlank(message = "El password no puede ir vacio")
    String password
) {

}
