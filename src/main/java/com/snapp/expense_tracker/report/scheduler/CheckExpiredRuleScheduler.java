package com.snapp.expense_tracker.report.scheduler;

import com.snapp.expense_tracker.common.enums.TimeUnit;
import com.snapp.expense_tracker.report.repository.RuleRepository;
import com.snapp.expense_tracker.report.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class CheckExpiredRuleScheduler {
    private final RuleRepository ruleRepository;
    private final RuleService ruleService;
    private Lock lock;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public CheckExpiredRuleScheduler(RuleRepository ruleRepository, RuleService ruleService) {
        this.ruleRepository = ruleRepository;
        this.ruleService = ruleService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public synchronized void checkDailyRules() {
        lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                ruleRepository.findByTimeUnitAndExpirationAtBefore(TimeUnit.DAY, LocalDateTime.now()).
                        forEach(ruleService::notifyExpiredRule);
            }catch (Exception e){
                logger.error("Error in daily expired rule,", e);
            }finally {
                lock.unlock();
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * 0")
    public synchronized void checkWeeklyRules() {
        lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                ruleRepository.findByTimeUnitAndExpirationAtBefore(TimeUnit.WEEK, LocalDateTime.now()).
                        forEach(ruleService::notifyExpiredRule);
            }catch (Exception e){
                logger.error("Error in weekly expired rule,", e);
            }finally {
                lock.unlock();
            }
        }
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public synchronized void checkMonthlyRules() {
        lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                ruleRepository.findByTimeUnitAndExpirationAtBefore(TimeUnit.MONTH, LocalDateTime.now()).
                        forEach(ruleService::notifyExpiredRule);
            }catch (Exception e){
                logger.error("Error in monthly expired rule,", e);
            }finally {
                lock.unlock();
            }
        }
    }
}
