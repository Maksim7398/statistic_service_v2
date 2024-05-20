package com.statistic_service.persist.repository;

import com.statistic_service.persist.entity.TransactionPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsPaymentsRepository extends JpaRepository<TransactionPayments, UUID> {

    @Query("FROM TransactionPayments t WHERE t.userIdSource = :userId AND t.createDate BETWEEN :startDate AND :endDate")
    List<TransactionPayments> transactionPaymentsByUserId(UUID userId, LocalDate startDate,LocalDate endDate);
}
