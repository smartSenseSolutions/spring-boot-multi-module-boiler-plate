/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Application security Config properties
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@ConfigurationProperties("app.security")
public record SecurityConfigProperties(Boolean enabled,
                                       String realm,
                                       String clientId,
                                       String roleClientId,
                                       String secret,
                                       String authServerUrl,
                                       String authUrl,
                                       String tokenUrl,
                                       String refreshTokenUrl,
                                       List<String> corsOrigins) {
}
