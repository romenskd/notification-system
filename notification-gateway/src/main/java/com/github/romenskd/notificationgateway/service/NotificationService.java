package com.github.romenskd.notificationgateway.service;

import com.github.romenskd.notificationgateway.dto.NotificationRequest;
import com.github.romenskd.notificationgateway.dto.NotificationResponce;
import com.github.romenskd.notificationgateway.kafka.event.NotificationEvent;
import com.github.romenskd.notificationgateway.kafka.producer.NotificationProducer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationProducer producer;
    private final RateLimitService rateLimitService;

    public NotificationResponce processNotification(@Valid NotificationRequest requestDto, String userId) {
        if (!rateLimitService.isRequestAllowed(userId)) {
            throw new IllegalStateException("Rate limit exceeded for user " + userId);
        }

        NotificationEvent event = toEvent(requestDto);
        producer.send(event);

        return NotificationResponce.builder()
                .notificationId(event.getRequestId())
                .status("PENDING")
                .build();
    }

    private NotificationEvent toEvent(NotificationRequest requestDto ) {
        return NotificationEvent.builder()
                .type(requestDto.getType())
                .priority(requestDto.getPriority())
                .message(requestDto.getMessage())
                .userIds(requestDto.getUserIds())
                .requestId(UUID.randomUUID().toString())
                .build();
    }
}
