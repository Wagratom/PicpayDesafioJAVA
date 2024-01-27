package com.expickpay.pickpay.dtos;

public record TransactionDTO(String senderId, String receiverId, double amount) {
}
