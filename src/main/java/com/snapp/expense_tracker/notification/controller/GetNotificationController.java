package com.snapp.expense_tracker.notification.controller;

import com.snapp.expense_tracker.common.model.SingleValueResponse;
import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.notification.model.GetNotificationRequest;
import com.snapp.expense_tracker.notification.model.NotificationView;
import com.snapp.expense_tracker.notification.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetNotificationController {
    private final NotificationService notificationService;

    public GetNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("api/notification")
    public Page<NotificationView> handle(@PageableDefault Pageable pageable) {
        GetNotificationRequest request = new GetNotificationRequest(SecurityUtil.getUserId(),
                pageable);
        return notificationService.getNotifications(request);
    }
}
