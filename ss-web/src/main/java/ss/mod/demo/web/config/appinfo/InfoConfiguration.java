/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config.appinfo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * App info configuration
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@ConfigurationProperties(prefix = "app")
public record InfoConfiguration(String name,
                                String description,
                                String version) {
}
