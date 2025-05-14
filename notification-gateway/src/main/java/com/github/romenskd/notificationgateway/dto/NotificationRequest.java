package com.github.romenskd.notificationgateway.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class NotificationRequest {
    @NotNull
    NotificationType type;

    @NotNull
    NotificationPriority priority;

    @NotBlank
    String message;

    @NotEmpty(message = "User list cannot be empty")
    List<Long> userIds;

}
