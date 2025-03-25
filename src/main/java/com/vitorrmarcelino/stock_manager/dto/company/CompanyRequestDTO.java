package com.vitorrmarcelino.stock_manager.dto.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyRequestDTO(@NotBlank(message = "All fields must be filled in") String name,
                                @NotBlank(message = "All fields must be filled in") @CNPJ(message = "Invalid CNPJ") String cnpj,
                                @Email(message = "Invalid email") String email,
                                @NotBlank(message = "All fields must be filled in") @Size(min = 4, max = 20, message =  "the password must be between 4 and 20 characters") String password,
                                @NotBlank(message = "All fields must be filled in") String confirmpassword) {
}
