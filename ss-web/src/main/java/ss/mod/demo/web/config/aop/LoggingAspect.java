/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config.aop;

import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Logging aspect that logs every request and response
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {


    private final HttpServletRequest request;

    @Pointcut("execution(* ss.mod.demo.resource.*.*(..))")
    public void printLogs() {
    }

    @SneakyThrows
    @Around("printLogs()")
    public Object logMethod(ProceedingJoinPoint joinPoint) {
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        String targetMethod = joinPoint.getSignature().getName();
        Thread currentThread = Thread.currentThread();
        String ipAddress = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("==> Request : {}, Class: {}, method(s): {}, Thread :{}, IpAddress :{} ",
                request.getRequestURL(), targetClass, targetMethod, currentThread.threadId(), ipAddress);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object response;
        try {
            response = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("<== Responding in time:{} ms, Thread :{} ", stopWatch.getTotalTimeMillis(), currentThread.threadId());
        }
        return response;
    }
}
