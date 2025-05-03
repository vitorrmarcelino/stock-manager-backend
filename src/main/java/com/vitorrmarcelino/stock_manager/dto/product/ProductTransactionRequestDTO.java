package com.vitorrmarcelino.stock_manager.dto.product;

import com.vitorrmarcelino.stock_manager.type.TransactionTypeEnum;
import jakarta.validation.constraints.NotNull;

public record ProductTransactionRequestDTO(@NotNull(message = "All fields must be filled in") Integer productId,
                                           @NotNull(message = "All fields must be filled in") Integer quantity,
                                           @NotNull(message = "All fields must be filled in") TransactionTypeEnum type){
}
