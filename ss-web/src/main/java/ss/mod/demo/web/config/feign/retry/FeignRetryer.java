/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config.feign.retry;

import feign.RetryableException;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Feign Retry implementation
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Slf4j
@RequiredArgsConstructor
public class FeignRetryer implements Retryer {
    private final long maxRetryAttempt;
    private final long retryInterval;
    private int attempt = 1;

    @Override
    public void continueOrPropagate(RetryableException e) {
        FeignRetryer.log.info("Feign retry attempt {} due to {} ", attempt, e.getMessage());
        if (attempt++ >= maxRetryAttempt) {
            throw new RuntimeException("Max retry attempt exceed");
        }
        try {
            Thread.sleep(retryInterval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() {
        return new FeignRetryer(maxRetryAttempt, retryInterval);
    }
}
