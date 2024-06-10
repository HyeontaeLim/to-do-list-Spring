package com.example.todolist.controller.errorDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ValidationResult {
    private final List<FieldErrorDetail> errors;
}
