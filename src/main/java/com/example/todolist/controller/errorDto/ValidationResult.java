package com.example.todolist.controller.errorDto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationResult {
    private final List<FieldErrorDetail> errors;

    public ValidationResult(BindingResult bindingResult, HttpServletResponse response, MessageSource messageSource) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        List<FieldErrorDetail> errors = new ArrayList<>();
        for (FieldError error : allErrors) {
            FieldErrorDetail fieldErrorDetail1 = new FieldErrorDetail(error.getField(), error.getCodes(), error.getRejectedValue(), messageSource.getMessage(error, response.getLocale()));
            errors.add(fieldErrorDetail1);
        }
        this.errors = errors;
    }
}
