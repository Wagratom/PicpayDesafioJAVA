package com.expickpay.pickpay.dtos;

import java.math.BigDecimal;

public record UserDto(
        String username,

        String cpf,
        String email,
        String password,

        String UserType,

        BigDecimal amount
) {
}
