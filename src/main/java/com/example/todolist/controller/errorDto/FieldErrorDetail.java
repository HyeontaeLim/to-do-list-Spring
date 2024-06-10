package com.example.todolist.controller.errorDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FieldErrorDetail {
    private final String field;
    private final String [] codes;
    private final Object rejectedValue;
    private final String message;

}
