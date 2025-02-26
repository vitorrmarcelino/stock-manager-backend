package com.vitorrmarcelino.stock_manager.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(
        @NotBlank(message = "All fields must be filled in") String email,
        @NotBlank(message = "All fields must be filled in") String password) {
}
