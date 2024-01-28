package com.expickpay.pickpay.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserDto(
        @NotBlank(message = "Username is mandatory")
        String username,

        @NotBlank(message = "Name is mandatory")
        String cpf,
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Password is mandatory")
        String password,

        @NotBlank(message = "UserType is mandatory")
        String UserType,

        @NotNull(message = "Amount is mandatory")
        BigDecimal amount
) {}
