package com.expickpay.pickpay.contollers;
import com.expickpay.pickpay.dtos.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionControllers {
    @PostMapping("/transaction")
    public ResponseEntity<String> ExecuteTransaction(@RequestBody TransactionDTO dto) {
        return ResponseEntity.ok("Transaction created");
    }
}
