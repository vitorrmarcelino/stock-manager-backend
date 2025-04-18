package com.vitorrmarcelino.stock_manager.dto.stock;

import jakarta.validation.constraints.NotBlank;

public record StockRequestDTO(@NotBlank(message = "All fields must be filled in") String name) {
}
