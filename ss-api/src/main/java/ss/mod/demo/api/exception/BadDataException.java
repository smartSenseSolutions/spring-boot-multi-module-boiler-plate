/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * Bad data exception
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@NoArgsConstructor
@Getter
public class BadDataException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5732404099105408974L;

    private String code;
    private String message;

    public BadDataException(String message) {
        this.message = message;
    }

    public BadDataException(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
