package com.statistic_service.persist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction_payments")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TransactionPayments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userIdSource;

    private UUID accountIdSource;

    @Column(name = "total_sum")
    private BigDecimal totalSum;

    private String category;

    private UUID userIdDestination;

    private UUID accountIdDestination;

    @Column(name = "bik_destination")
    private Integer bikDestination;

    @Column(name = "internalTransfer")
    private Boolean internalTransfer;

    @Column(name = "create_date")
    private LocalDate createDate;
}
