package com.statistic_service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DatePeriod {

    @NotNull(message = "start date must not be null")
    private LocalDate startDate;

    @NotNull(message = "end date must not be null")
    private LocalDate endDate;
}
