package com.statistic_service.service;

import com.statistic_service.controller.request.DatePeriod;
import com.statistic_service.persist.entity.TransactionPayments;
import com.statistic_service.persist.repository.TransactionsPaymentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionsStatisticService {

    private final TransactionsPaymentsRepository repository;

    public Map<String, Double> purchaseStatistic(final UUID userID, DatePeriod datePeriod) {
        System.out.println(repository.transactionPaymentsByUserId(userID, datePeriod.getStartDate(), datePeriod.getEndDate()));
        return repository.transactionPaymentsByUserId(userID, datePeriod.getStartDate(), datePeriod.getEndDate()).stream()
                .filter(t -> t.getInternalTransfer().equals(false))
                .collect(Collectors.groupingBy(
                        TransactionPayments::getCategory,
                        Collectors.summingDouble(t -> t.getTotalSum().doubleValue()))
                );
    }

    public Map<String, Double> transferStatistic(final UUID userID, DatePeriod datePeriod) {
        System.out.println(repository.transactionPaymentsByUserId(userID, datePeriod.getStartDate(), datePeriod.getEndDate()));
        return repository.transactionPaymentsByUserId(userID, datePeriod.getStartDate(), datePeriod.getEndDate()).stream()
                .filter(t -> t.getInternalTransfer().equals(true))
                .collect(Collectors.groupingBy(
                        TransactionPayments::getCategory,
                        Collectors.summingDouble(t -> t.getTotalSum().doubleValue()))
                );
    }
}
