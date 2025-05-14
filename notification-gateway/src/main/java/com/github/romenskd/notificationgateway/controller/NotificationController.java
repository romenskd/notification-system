package com.github.romenskd.notificationgateway.controller;

import com.github.romenskd.notificationgateway.dto.NotificationRequest;
import com.github.romenskd.notificationgateway.dto.NotificationResponce;
import com.github.romenskd.notificationgateway.service.NotificationService;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notify")
    public NotificationResponce sendNotification(@RequestBody @Valid NotificationRequest request,
                                                 Authentication authentication) {
        String userId = (String) authentication.getPrincipal();

        return notificationService.processNotification(request, userId);
    }
}
