package com.expickpay.pickpay.service;

import com.expickpay.pickpay.dtos.TransactionDTO;
import com.expickpay.pickpay.entity.TransactionEntity;
import com.expickpay.pickpay.entity.UserEntity;
import com.expickpay.pickpay.excecoes.InvalidTransactionException;
import com.expickpay.pickpay.reposity.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public List<TransactionEntity> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    public TransactionEntity createTransaction(TransactionDTO dto){
        UserEntity userReceiver = this.userService.getUserById(dto.receiverId());
        UserEntity userSender = this.userService.getUserById(dto.senderId());

        isValidSender(userSender, dto.amount());
        validTransactionOnAPI();

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(dto.amount());
        transaction.setSender(userSender);
        transaction.setReceiver(userReceiver);
        transaction.setDate(LocalDateTime.now());
        this.userService.updateAmount(userSender, userReceiver, dto.amount());

        sendNotification(transaction);
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

    public void validTransactionOnAPI() {
        ResponseEntity<Map> AuthorizationResponse = this.restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        System.out.println(AuthorizationResponse);
        System.out.println();
        System.out.println(AuthorizationResponse.getBody());
        if (AuthorizationResponse.getStatusCode() == HttpStatus.OK) {
            String messageResponse = (String) AuthorizationResponse.getBody().get("message");
            if (messageResponse != null && messageResponse.equals("Autorizado")) {
                return;
            }
        }
        throw new InvalidTransactionException("Transaction not authorized by the API");
    }

    public void sendNotification(TransactionEntity transaction) {
        String message = "Você recebeu uma transferência de " + transaction.getAmount() + " de " + transaction.getSender().getUsername();
        ResponseEntity<Map> statusNotification = this.restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", message, Map.class);
        if (statusNotification.getStatusCode() == HttpStatus.OK) {
            Boolean messageResponse = (Boolean) statusNotification.getBody().get("message");
            if (messageResponse != null && messageResponse.equals(true)) {
                return;
            }
        }
        throw new InvalidTransactionException("Notification not sent by the API");
    }
}
