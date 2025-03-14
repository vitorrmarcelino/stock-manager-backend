package com.vitorrmarcelino.stock_manager.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeeRequestDTO(@NotBlank(message = "All fields must be filled in") String name,
                                 @NotBlank(message = "All fields must be filled in") @CPF(message = "Invalid CPF") String cpf,
                                 @Email(message = "Invalid email") String email) {
}
