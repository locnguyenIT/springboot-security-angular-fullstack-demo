package com.example.demo.exception;

import com.example.demo.student.StudentController;
import com.example.demo.user.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // the methods in an @ControllerAdvice apply globally to all controllers.
@RestController
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationBadRequestException {

    @ExceptionHandler(MethodArgumentNotValidException.class) //catch Exception on MethodArgumentNotValidException.class when data of object invalid
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.put("message", errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class) //catch Exception on ConstraintViolationException.class when param is invalid
    public Map<String, String> handleConstraintValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            String errorMessage = error.getMessage();
            errors.put("message", errorMessage);
        });
        return errors;
    }
}
