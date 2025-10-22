package com.snapp.expense_tracker.notification.eventHandler;

import com.snapp.expense_tracker.common.event.RuleMetEvent;
import com.snapp.expense_tracker.notification.domain.Notification;
import com.snapp.expense_tracker.notification.repository.NotificationRepository;
import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.stereotype.Component;


@Component
public class NotificationListener {
    private final NotificationRepository notificationRepository;

    public NotificationListener(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @DomainEventHandler
    public void onRuleMet(RuleMetEvent event) {
        Notification notification = new Notification();
        notification.setCategoryName(event.categoryName());
        notification.setSubcategoryName(event.subcategoryName());
        notification.setMessage(event.description());
        notification.setUserId(event.userId());
        notification.setRuleId(event.ruleId());
        notification.setSeen(false);
        notification.setType(event.notificationType());
        notificationRepository.save(notification);
    }

}
