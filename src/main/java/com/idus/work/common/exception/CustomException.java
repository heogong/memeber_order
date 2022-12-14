package com.idus.work.common.exception;

import javassist.bytecode.DuplicateMemberException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.module.FindException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        errors.put("message", "Invalid value!");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({DuplicateMemberException.class, FindException.class, UsernameNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleDuplicateKeyException(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
        errors.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
