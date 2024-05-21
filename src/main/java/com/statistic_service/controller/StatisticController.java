package com.statistic_service.controller;

import com.statistic_service.controller.request.DatePeriod;
import com.statistic_service.controller.response.StatisticResponse;
import com.statistic_service.service.TransactionsStatisticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StatisticController {

    private final TransactionsStatisticService service;

    @PostMapping("/purchaseStatistic/{userId}")
    public List<StatisticResponse> getPurchaseStatistic(@PathVariable UUID userId, @RequestBody @Valid DatePeriod datePeriod) {
        return service.purchaseStatistic(userId, datePeriod).entrySet()
                .stream().map(k -> {
                            return StatisticResponse.builder()
                                    .category(k.getKey())
                                    .summa(BigDecimal.valueOf(k.getValue()))
                                    .build();
                        }
                ).toList();
    }

    @PostMapping("/transferStatistic/{userId}")
    public List<StatisticResponse> getTransferStatistic(@PathVariable UUID userId, @RequestBody @Valid DatePeriod datePeriod) {
        return service.transferStatistic(userId, datePeriod).entrySet()
                .stream().map(k -> {
                            return StatisticResponse.builder()
                                    .category(k.getKey())
                                    .summa(BigDecimal.valueOf(k.getValue()))
                                    .build();
                        }
                ).toList();
    }
}
