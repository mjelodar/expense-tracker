package com.snapp.expense_tracker.notification.service;

import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.notification.domain.Notification;
import com.snapp.expense_tracker.notification.exception.NotificationNotFoundException;
import com.snapp.expense_tracker.notification.model.GetNotificationRequest;
import com.snapp.expense_tracker.notification.model.NotificationView;
import com.snapp.expense_tracker.notification.model.mapper.NotificationMapper;
import com.snapp.expense_tracker.notification.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Page<NotificationView> getNotifications(GetNotificationRequest request) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(request.getUserId(), request.getPageable()).
                map(NotificationMapper::toNotificationView);
    }

    public Integer countOfUnseenNotifications(Long userId) {
        return notificationRepository.countByUserIdAndSeen(userId, false);
    }

    public void seen(Long id) {
        Notification notification = notificationRepository.findByUserIdAndId(SecurityUtil.getUserId(), id).orElseThrow(NotificationNotFoundException::new);
        notification.setSeen(true);
        notificationRepository.save(notification);
    }

    public void unSeen(Long id) {
        Notification notification = notificationRepository.findByUserIdAndId(SecurityUtil.getUserId(), id).orElseThrow(NotificationNotFoundException::new);
        notification.setSeen(false);
        notificationRepository.save(notification);
    }
}
