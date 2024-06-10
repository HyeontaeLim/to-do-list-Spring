package com.validation;

import com.example.todolist.controller.dto.AddUpdateMemoForm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        AddUpdateMemoForm addUpdateMemoForm = new AddUpdateMemoForm();
        addUpdateMemoForm.setMemo("");
        addUpdateMemoForm.setDTime(LocalDateTime.now());

        Set<ConstraintViolation<AddUpdateMemoForm>> violations = validator.validate(addUpdateMemoForm);
        for (ConstraintViolation<AddUpdateMemoForm> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }
    }
}
