package com.expickpay.pickpay.dtos;

public record UserDto(
        String username,

        String cpf,
        String email,
        String password,

        String UserType
) {
}
