package com.expickpay.pickpay.contollers;
import com.expickpay.pickpay.dtos.TransactionDTO;
import com.expickpay.pickpay.entity.TransactionEntity;
import com.expickpay.pickpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
