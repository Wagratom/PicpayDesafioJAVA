package com.expickpay.pickpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal amount) {
}
