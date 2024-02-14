/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Feign Retry property
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@ConfigurationProperties("feign.default")
@Getter
@AllArgsConstructor
public class FeignProperties {

    private final long connectionTimeoutMillis;
    private final long readTimeoutMillis;
    private final int maxIdleConnections;
    private final long keepAliveDurationMinutes;
    private final int maxRetryAttempt;
    private final long retryInterval;
}
