/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.exception;

import lombok.Builder;

import java.util.List;
import java.util.Map;


/**
 * Common Error API response
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Builder
public record ErrorObject(String code, String message, int status, Map<String, List<String>> errorParams) {
}
