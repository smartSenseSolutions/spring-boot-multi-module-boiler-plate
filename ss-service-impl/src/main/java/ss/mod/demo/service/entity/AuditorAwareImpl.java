/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.service.entity;

import org.springframework.data.domain.AuditorAware;
import ss.mod.demo.api.constant.ContValue;

import java.util.Optional;

/**
 * Provide audit related information for auditable entity
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Fetch UserId from SecurityContext and return Optional Value
     *
     * @return Optional of {@link String}
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        String authUserId = "";
        return !authUserId.isEmpty() ? Optional.of(authUserId) : Optional.of(ContValue.ANONYMOUS);
    }
}
