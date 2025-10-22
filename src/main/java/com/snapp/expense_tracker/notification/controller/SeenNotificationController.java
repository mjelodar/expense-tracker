package com.snapp.expense_tracker.notification.controller;

import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.notification.model.GetNotificationRequest;
import com.snapp.expense_tracker.notification.model.NotificationView;
import com.snapp.expense_tracker.notification.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeenNotificationController {
    private final NotificationService notificationService;

    public SeenNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PutMapping("api/notification/seen/{id}")
    public void handle(@PathVariable Long id) {
        notificationService.seen(id);
    }
}
