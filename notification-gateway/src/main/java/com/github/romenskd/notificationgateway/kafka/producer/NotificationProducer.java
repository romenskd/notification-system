package com.github.romenskd.notificationgateway.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romenskd.notificationgateway.kafka.event.NotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.notification-topic}")
    private String topic;

    @Transactional
    public void send(NotificationEvent notificationEvent) {
        try {
            String json = objectMapper.writeValueAsString(notificationEvent);

            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, json);
            future.thenAccept(sendResult -> {
                log.info("Sent notification event successfully: {}", notificationEvent);
            }).exceptionally(ex -> {
                log.error("Failed to send notification event", ex);
                return null;
            });

        } catch (JsonProcessingException e) {
            log.error("Failed to serialize notification event: {}", notificationEvent, e);
        }
    }
}
