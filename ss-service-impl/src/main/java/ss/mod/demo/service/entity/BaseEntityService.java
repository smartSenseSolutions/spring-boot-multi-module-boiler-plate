/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.service.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartsensesolutions.java.commons.base.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Base entity service with common required function
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, ID> extends com.smartsensesolutions.java.commons.base.service.BaseService<E, ID> implements ServiceUtil {
    private final ObjectMapper objectMapper;
    private final MessageSource messageSource;

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }
}
