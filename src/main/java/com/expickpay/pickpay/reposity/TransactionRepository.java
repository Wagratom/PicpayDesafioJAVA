package com.expickpay.pickpay.reposity;

import com.expickpay.pickpay.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
