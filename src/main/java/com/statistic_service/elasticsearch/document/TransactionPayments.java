package com.statistic_service.elasticsearch.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Document(indexName = "transaction_payments")
@Getter
@Setter
@ToString
@Builder
public class TransactionPayments {

    @Id
    private String id;

    @Field(name = "user_id_source")
    private UUID userIdSource;

    @Field(name = "account_id_source")
    private UUID accountIdSource;

    @Field(name = "total_sum")
    private BigDecimal totalSum;

    @Field(name = "category")
    private String category;

    @Field(name = "user_id_destination")
    private UUID userIdDestination;

    @Field(name = "account_id_destination")
    private UUID accountIdDestination;

    @Field(name = "bik_destination")
    private Integer bikDestination;

    @Field(name = "internalTransfer")
    private Boolean internalTransfer;

    @Field(type = FieldType.Date,format = DateFormat.basic_date,pattern = "yyyy-MM-dd", name = "create_date")
    private LocalDate createDate;

}
