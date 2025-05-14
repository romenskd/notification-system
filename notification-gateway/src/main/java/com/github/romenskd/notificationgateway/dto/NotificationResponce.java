package com.github.romenskd.notificationgateway.dto;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class NotificationResponce {
    String notificationId;
    String status;
}
