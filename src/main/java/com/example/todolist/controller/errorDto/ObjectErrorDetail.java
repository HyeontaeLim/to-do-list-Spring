package com.example.todolist.controller.errorDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ObjectErrorDetail {
    private final String objectName;
    private final String [] codes;
    private final String message;
}
