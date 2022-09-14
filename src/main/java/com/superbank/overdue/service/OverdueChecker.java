package com.superbank.overdue.service;

import com.superbank.credit.service.CreditService;
import com.superbank.overdue.dto.OverdueDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OverdueChecker {
    private final Logger LOGGER = LoggerFactory.getLogger(OverdueChecker.class);

    private final CreditService creditService;
    private final String                            kafkaTopic;
    private final KafkaTemplate<String, OverdueDto> kafkaTemplate;

    public OverdueChecker(final CreditService creditService,
                          final @Value("${topics.overdue.name}") String kafkaTopic,
                          final KafkaTemplate<String, OverdueDto> kafkaTemplate) {
        this.creditService = creditService;
        this.kafkaTopic = kafkaTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void checkOverdue(){
        final OverdueDto overdueDto = new OverdueDto(1000D, LocalDate.now(), 125L);
        kafkaTemplate.send(kafkaTopic, overdueDto);
    }
}
