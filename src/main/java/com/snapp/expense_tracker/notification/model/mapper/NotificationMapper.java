package com.snapp.expense_tracker.notification.model.mapper;

import com.snapp.expense_tracker.notification.domain.Notification;
import com.snapp.expense_tracker.notification.model.NotificationView;

public class NotificationMapper {
    public static NotificationView toNotificationView(Notification notification) {
        return new NotificationView(notification.getId(),
                notification.getType(),
                notification.getMessage(),
                notification.getSeen());
    }
}
