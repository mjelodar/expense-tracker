package com.snapp.expense_tracker.report.scheduler;

import com.snapp.expense_tracker.common.enums.TimeUnit;
import com.snapp.expense_tracker.report.repository.RuleRepository;
import com.snapp.expense_tracker.report.service.RuleService;
import org.redisson.api.RedissonClient;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CheckExpiredRuleScheduler {
    private final RuleRepository ruleRepository;
    private final RuleService ruleService;
    private final RedissonClient redissonClient;;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public CheckExpiredRuleScheduler(RuleRepository ruleRepository,
                                     RuleService ruleService,
                                     RedissonClient redissonClient) {
        this.ruleRepository = ruleRepository;
        this.ruleService = ruleService;
        this.redissonClient = redissonClient;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public synchronized void checkDailyRules() {
        RLock lock = redissonClient.getLock("DAILY");
        if (lock.tryLock()) {
            try {
                ruleRepository.findByTimeUnitAndExpirationAtBefore(TimeUnit.DAY, LocalDateTime.now()).
                        parallelStream().
                        forEach(ruleService::notifyExpiredRule);
            }catch (Exception e){
                logger.error("Error in daily expired rule,", e);
            }finally {
                lock.unlock();
            }
        }
    }

    //TODO think about better way to find weekly/monthly record that needs to be notified
    @Scheduled(cron = "0 0 */23 * * *")
    public synchronized void checkWeeklyRules() {
        RLock lock = redissonClient.getLock("WEEKLY");
        if (lock.tryLock()) {
            try {
                ruleRepository.findByTimeUnitAndExpirationAtBefore(TimeUnit.WEEK, LocalDateTime.now()).
                        parallelStream().
                        forEach(ruleService::notifyExpiredRule);
            }catch (Exception e){
                logger.error("Error in weekly expired rule,", e);
            }finally {
                lock.unlock();
            }
        }
    }

    @Scheduled(cron = "0 30 */23 * * *")
    public synchronized void checkMonthlyRules() {
        RLock lock = redissonClient.getLock("MONTHLY");
        if (lock.tryLock()) {
            try {
                ruleRepository.findByTimeUnitAndExpirationAtBefore(TimeUnit.MONTH, LocalDateTime.now()).
                        parallelStream().
                        forEach(ruleService::notifyExpiredRule);
            }catch (Exception e){
                logger.error("Error in monthly expired rule,", e);
            }finally {
                lock.unlock();
            }
        }
    }
}
