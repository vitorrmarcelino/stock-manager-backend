package com.vitorrmarcelino.stock_manager.dto.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyUpdateRequestDTO(@NotBlank(message = "All fields must be filled in") String name,
                                      @NotBlank(message = "All fields must be filled in") @CNPJ(message = "Invalid CNPJ") String cnpj,
                                      @Email(message = "Invalid email") String email) {
}
