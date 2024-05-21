package com.statistic_service.elasticsearch.repository;

import com.statistic_service.elasticsearch.document.TransactionPayments;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionPaymentsElasticRepository extends ElasticsearchRepository<TransactionPayments, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"user_id_source\": \"?0\"}}," +
            "{\"range\" : {\"create_date\":{\"gte\": \"?1\",\"lte\": \"?2\"}}}]}}}")
    List<TransactionPayments> findAllByUserSourceId(UUID userId, String startDate, String endDate);

}
