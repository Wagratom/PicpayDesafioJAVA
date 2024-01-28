package com.expickpay.pickpay.service;

import com.expickpay.pickpay.dtos.TransactionDTO;
import com.expickpay.pickpay.entity.TransactionEntity;
import com.expickpay.pickpay.entity.UserEntity;
import com.expickpay.pickpay.excecoes.InvalidTransactionException;
import com.expickpay.pickpay.reposity.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public TransactionEntity createTransaction(TransactionDTO dto){
        UserEntity userReceiver = this.userService.getUserById(dto.receiverId());
        UserEntity userSender = this.userService.getUserById(dto.senderId());

        isValidSender(userSender, dto.amount());

        TransactionEntity transaction = new TransactionEntity();

        transaction.setAmount(dto.amount());
        transaction.setSender(userSender);
        transaction.setReceiver(userReceiver);
        transaction.setDate(LocalDateTime.now());
        this.userService.updateAmount(userSender, dto.amount());
        return this.transactionRepository.save(transaction);
    }

    public void isValidSender(UserEntity user, BigDecimal amount){
        if (user.getUserType().name().equals("MECHAN")){
            throw new InvalidTransactionException("Merchant cannot sender money");
        }
        if (user.getAmount().compareTo(amount) < 0) {
            throw new InvalidTransactionException("Insufficient funds");
        }
    }
}
