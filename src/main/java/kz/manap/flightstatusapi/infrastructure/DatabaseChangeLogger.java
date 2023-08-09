package kz.manap.flightstatusapi.infrastructure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DatabaseChangeLogger {

    private final Logger logger = LogManager.getLogger(DatabaseChangeLogger.class);

    @Before("execution(* kz.manap.flightstatusapi.repositories.*.save(..)) || " +
            "execution(* kz.manap.flightstatusapi.repositories.*.delete(..))")
    public void logDatabaseChanges(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Database change: " + methodName);
    }
}

