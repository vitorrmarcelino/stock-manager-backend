package com.vitorrmarcelino.stock_manager.dto.product;

import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(@NotBlank(message = "All fields must be filled in") String name) {
}
