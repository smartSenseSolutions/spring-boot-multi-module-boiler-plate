/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * {@code MultipartFileSizeValidator} is validator class for validation of the size of the uploaded file.
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
public class MultipartFileSizeValidator implements ConstraintValidator<MultipartFileSize, MultipartFile> {

    /**
     * Minimum size of file
     */
    private long min;

    /**
     * Maximum size of file
     */
    private long max;

    @Override
    public void initialize(MultipartFileSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !(value.getSize() < min || value.getSize() > max);
    }
}