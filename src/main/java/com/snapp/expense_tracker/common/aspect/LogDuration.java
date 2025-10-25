package com.snapp.expense_tracker.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogDuration {
    private static final Logger logger = LoggerFactory.getLogger(LogDuration.class);
    private static final String LOG_PATTERN = "Duration of processing request is %s ms. [URL:%s]";

    @Around(value = "@within(org.springframework.web.bind.annotation.RestController)")
    public Object doLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        try {
            result = proceedingJoinPoint.proceed();
            logger.info(LOG_PATTERN.formatted(stopWatch.getTotalTimeMillis(), requestURI));
            return result;
        }catch (Throwable throwable){
            logger.error(LOG_PATTERN.formatted(stopWatch.getTotalTimeMillis(), requestURI), throwable);
            throw throwable;
        }finally {
            stopWatch.stop();
        }
    }
}
