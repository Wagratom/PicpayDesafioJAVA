package com.expickpay.pickpay.contollers;
import com.expickpay.pickpay.dtos.TransactionDTO;
import com.expickpay.pickpay.entity.TransactionEntity;
import com.expickpay.pickpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionControllers {

    private final TransactionService transactionService;

    @Autowired
    public TransactionControllers(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/newTransaction")
    public ResponseEntity<TransactionEntity> ExecuteTransaction(@RequestBody TransactionDTO dto) {
        TransactionEntity transaction = this.transactionService.createTransaction(dto);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionEntity>> getAllTransactions() {
        List<TransactionEntity> transactions = this.transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
