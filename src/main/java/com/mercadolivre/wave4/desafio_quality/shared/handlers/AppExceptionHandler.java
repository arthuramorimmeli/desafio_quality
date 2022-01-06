package com.mercadolivre.wave4.desafio_quality.shared.handlers;

import com.mercadolivre.wave4.desafio_quality.dtos.ErrorMessageDTO;
import com.mercadolivre.wave4.desafio_quality.dtos.InvalidParamsDTO;
import com.mercadolivre.wave4.desafio_quality.shared.exceptions.CreatePropertyException;
import com.mercadolivre.wave4.desafio_quality.shared.exceptions.PropertyNotFoundException;
import com.mercadolivre.wave4.desafio_quality.shared.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public InvalidParamsDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.put(fieldName, errorMessage);
        });
        return InvalidParamsDTO.builder()
                .message("Invalid params")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .arguments(errors)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(constraintViolation -> {
            errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        });
        return errors;
    }

    @ExceptionHandler(value = CreatePropertyException.class)
    protected ResponseEntity<Object> handlePersistencia(CreatePropertyException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = PropertyNotFoundException.class)
    protected ResponseEntity<Object> handlePersistencia(PropertyNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
