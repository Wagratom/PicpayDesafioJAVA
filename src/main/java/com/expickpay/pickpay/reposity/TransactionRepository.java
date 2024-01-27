package com.expickpay.pickpay.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionRepository, Long> {
}
