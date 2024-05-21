package com.statistic_service.service;

import com.statistic_service.controller.request.DatePeriod;
import com.statistic_service.elasticsearch.repository.TransactionPaymentsElasticRepository;
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

    private final TransactionPaymentsElasticRepository elasticRepository;

    public Map<String, Double> purchaseStatistic(final UUID userID, DatePeriod datePeriod) {
        String startDate = datePeriod.getStartDate().toString().replaceAll("-", "");
        String endDate = datePeriod.getEndDate().toString().replaceAll("-", "");

        return elasticRepository.findAllByUserSourceId(userID, startDate, endDate).
                stream().filter(t -> t.getInternalTransfer().equals(false))
                .collect(Collectors.groupingBy(
                        com.statistic_service.elasticsearch.document.TransactionPayments::getCategory,
                        Collectors.summingDouble(t -> t.getTotalSum().doubleValue())
                ));
    }

    public Map<String, Double> transferStatistic(final UUID userID, DatePeriod datePeriod) {
        String startDate = datePeriod.getStartDate().toString().replaceAll("-", "");
        String endDate = datePeriod.getEndDate().toString().replaceAll("-", "");

        return elasticRepository.findAllByUserSourceId(userID, startDate, endDate).
                stream().filter(t -> t.getInternalTransfer().equals(true))
                .collect(Collectors.groupingBy(
                        com.statistic_service.elasticsearch.document.TransactionPayments::getCategory,
                        Collectors.summingDouble(t -> t.getTotalSum().doubleValue())
                ));
    }
}
