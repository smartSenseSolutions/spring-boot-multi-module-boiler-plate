/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * {@code MultipartFileSize} annotation is for validation of size of the multipart file.
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Documented
@Constraint(validatedBy = {MultipartFileSizeValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartFileSize {

    /**
     * Validation Message
     *
     * @return validation message
     */
    String message() default "The size of the files must be between {min} and {max} bytes.";

    /**
     * Minimum size of uploaded file.
     *
     * @return minimum size
     */
    long min() default 0;

    /**
     * Maximum size of uploaded file
     *
     * @return max size
     */
    long max() default Long.MAX_VALUE;

    /**
     * @return class
     */
    Class<?>[] groups() default {};

    /**
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};
}