package com.github.romenskd.notificationgateway.kafka.event;

import com.github.romenskd.notificationgateway.dto.NotificationPriority;
import com.github.romenskd.notificationgateway.dto.NotificationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class NotificationEvent {
    private NotificationType type;
    private NotificationPriority priority;
    private String message;
    private List<Long> userIds;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String requestId;
}
