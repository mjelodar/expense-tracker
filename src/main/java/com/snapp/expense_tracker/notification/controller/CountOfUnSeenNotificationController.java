package com.snapp.expense_tracker.notification.controller;

import com.snapp.expense_tracker.common.model.SingleValueResponse;
import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.notification.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountOfUnSeenNotificationController {
    private final NotificationService notificationService;

    public CountOfUnSeenNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("api/notification/unseen/count")
    public SingleValueResponse<Integer> handle() {
        return new SingleValueResponse<>(notificationService.countOfUnseenNotifications(SecurityUtil.getUserId()));
    }
}
