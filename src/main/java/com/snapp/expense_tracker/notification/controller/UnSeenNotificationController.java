package com.snapp.expense_tracker.notification.controller;

import com.snapp.expense_tracker.notification.service.NotificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnSeenNotificationController {
    private final NotificationService notificationService;

    public UnSeenNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PutMapping("api/notification/seen/{id}")
    public void handle(@PathVariable Long id) {
        notificationService.unSeen(id);
    }
}
