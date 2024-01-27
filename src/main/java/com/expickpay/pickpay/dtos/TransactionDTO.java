package com.expickpay.pickpay.dtos;

public record TransactionDTO(Long senderId, Long receiverId, Double amount) {
}
