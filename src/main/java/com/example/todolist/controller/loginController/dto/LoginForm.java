package com.example.todolist.controller.loginController.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
