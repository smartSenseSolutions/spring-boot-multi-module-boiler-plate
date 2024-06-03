/*
 * Copyright 2024 smartSense Consulting Solutions Pvt. Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ss.mod.demo.service.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartsensesolutions.commons.dao.base.BaseEntity;
import com.smartsensesolutions.commons.dao.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Base entity service with common required function
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
public abstract class BaseEntityService<E extends BaseEntity, ID> extends BaseService<E, ID> implements ServiceUtil {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }
}
