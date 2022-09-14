package com.superbank.overdue.listener;

import com.superbank.overdue.dto.OverdueDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OverdueListener {
    private final Logger LOGGER = LoggerFactory.getLogger(OverdueListener.class);

    @KafkaListener(topics = "${topic.name}")
    public void overdue(final OverdueDto overdueDto){
        LOGGER.info(overdueDto.toString());
    }
}
