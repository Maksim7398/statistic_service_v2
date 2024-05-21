package com.statistic_service.service;

import com.statistic_service.controller.request.DatePeriod;
import com.statistic_service.elasticsearch.document.TransactionPayments;
import com.statistic_service.elasticsearch.repository.TransactionPaymentsElasticRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionStatisticServiceTest {

    @Mock
    private TransactionPaymentsElasticRepository repository;

    @InjectMocks
    private TransactionsStatisticService underTest;

    @Test
    public void purchaseStatisticsTest() {
        TransactionPayments transactionPayments = TransactionPayments.builder()
                .category("products")
                .accountIdDestination(UUID.randomUUID())
                .accountIdSource(UUID.randomUUID())
                .bikDestination(11111)
                .createDate(LocalDate.now())
                .userIdDestination(UUID.randomUUID())
                .userIdSource(UUID.randomUUID())
                .totalSum(new BigDecimal("100"))
                .internalTransfer(false)
                .build();
        TransactionPayments transactionPayments2 = TransactionPayments.builder()
                .category("products")
                .accountIdDestination(UUID.randomUUID())
                .accountIdSource(UUID.randomUUID())
                .bikDestination(11111)
                .createDate(LocalDate.now())
                .userIdDestination(UUID.randomUUID())
                .userIdSource(UUID.randomUUID())
                .totalSum(new BigDecimal("100"))
                .internalTransfer(false)
                .build();

        when(repository.findAllByUserSourceId(any(), any(), any())).thenReturn(List.of(transactionPayments, transactionPayments2));

        Map<String, Double> stringDoubleMap = underTest.purchaseStatistic(transactionPayments.getUserIdSource(),
                DatePeriod.builder()
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now()).build());

        Assertions.assertEquals(200, stringDoubleMap.get(transactionPayments.getCategory()));
    }

    @Test
    public void transferStatisticsTest() {
        TransactionPayments transactionPayments = TransactionPayments.builder()
                .category("ВНУТРИБАНКОВСКИЙ ПЕРЕВОД")
                .accountIdDestination(UUID.randomUUID())
                .accountIdSource(UUID.randomUUID())
                .bikDestination(11111)
                .createDate(LocalDate.now())
                .userIdDestination(UUID.randomUUID())
                .userIdSource(UUID.randomUUID())
                .totalSum(new BigDecimal("100"))
                .internalTransfer(true)
                .build();
        TransactionPayments transactionPayments2 = TransactionPayments.builder()
                .category("ВНУТРИБАНКОВСКИЙ ПЕРЕВОД")
                .accountIdDestination(UUID.randomUUID())
                .accountIdSource(UUID.randomUUID())
                .bikDestination(11111)
                .createDate(LocalDate.now())
                .userIdDestination(UUID.randomUUID())
                .userIdSource(UUID.randomUUID())
                .totalSum(new BigDecimal("100"))
                .internalTransfer(false)
                .build();

        when(repository.findAllByUserSourceId(any(), any(), any())).thenReturn(List.of(transactionPayments, transactionPayments2));

        Map<String, Double> stringDoubleMap = underTest.purchaseStatistic(transactionPayments.getUserIdSource(),
                DatePeriod.builder()
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now()).build());

        Assertions.assertEquals(100, stringDoubleMap.get(transactionPayments.getCategory()));
    }
}
