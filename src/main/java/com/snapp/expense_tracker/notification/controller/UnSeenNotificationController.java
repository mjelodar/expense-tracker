package com.snapp.expense_tracker.notification.controller;

import com.snapp.expense_tracker.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Notification Module")
public class UnSeenNotificationController {
    private final NotificationService notificationService;

    public UnSeenNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PutMapping("api/notification/unseen/{id}")
    public void handle(@PathVariable Long id) {
        notificationService.unSeen(id);
    }
}
