/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ss.mod.demo.api.constant.ContMessage;
import ss.mod.demo.api.exception.BadDataException;
import ss.mod.demo.api.exception.ErrorObject;
import ss.mod.demo.service.entity.BaseService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler extends BaseService {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorObject> validatorExceptionHandle(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
        return new ResponseEntity<>(
                ErrorObject.builder()
                        .message(resolveMessage(ex.getBody().getDetail()))
                        .errorParams(errors)
                        .code("400-1")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorObject> validatorExceptionHandle(ConstraintViolationException ex) {
        Map<String, List<String>> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getPropertyPath().toString(), Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));

        return new ResponseEntity<>(
                ErrorObject.builder()
                        .message(ex.getMessage())
                        .errorParams(errors)
                        .code("400-2")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadDataException.class})
    public ResponseEntity<ErrorObject> badDataExceptionHandle(BadDataException ex) {
        return new ResponseEntity<>(
                ErrorObject.builder()
                        .message(resolveMessage(ex.getMessage()))
                        .code(ex.getCode() != null ? ex.getCode() : "400-3")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorObject> globalHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                ErrorObject.builder()
                        .message(resolveMessage(ContMessage.SOMETHING_WENT_WRONG))
                        .code("500-1")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
