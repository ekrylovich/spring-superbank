package com.superbank.technical.kafka;

import com.superbank.overdue.dto.OverdueDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, OverdueDto> producerFactory(final KafkaProperties kafkaProperties) {
        final Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, OverdueDto> kafkaTemplate(final ProducerFactory<String, OverdueDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic topic(final @Value("${topics.overdue.name}") String kafkaTopic) {
        return TopicBuilder
                .name(kafkaTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
