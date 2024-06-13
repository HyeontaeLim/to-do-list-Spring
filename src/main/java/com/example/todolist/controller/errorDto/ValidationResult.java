package com.example.todolist.controller.errorDto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationResult {
    private final List<FieldErrorDetail> fieldErrors;
    private final List<ObjectErrorDetail> globalErrors;

    public ValidationResult(BindingResult bindingResult, HttpServletResponse response, MessageSource messageSource) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        List<FieldError> allFieldErrors = bindingResult.getFieldErrors();
        List<FieldErrorDetail> fieldErrors = new ArrayList<>();
        for (FieldError error : allFieldErrors) {
            FieldErrorDetail fieldErrorDetail1 = new FieldErrorDetail(error.getField(), error.getCodes(), error.getRejectedValue(), messageSource.getMessage(error, response.getLocale()));
            fieldErrors.add(fieldErrorDetail1);
        }
        this.fieldErrors = fieldErrors;

        List<ObjectError> allGlobalErrors = bindingResult.getGlobalErrors();
        List<ObjectErrorDetail> globalErrors = new ArrayList<>();
        for (ObjectError error : allGlobalErrors) {
            ObjectErrorDetail objectErrorDetail = new ObjectErrorDetail(error.getObjectName(), error.getCodes(), messageSource.getMessage(error, response.getLocale()));
            globalErrors.add(objectErrorDetail);
        }
        this.globalErrors = globalErrors;
    }
}
