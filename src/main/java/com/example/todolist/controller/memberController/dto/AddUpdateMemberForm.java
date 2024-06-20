package com.example.todolist.controller.memberController.dto;

import com.example.todolist.domain.member.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddUpdateMemberForm {
    @NotBlank
    @Pattern(regexp = "^(?=.{5,20}$)(?=.*[A-Za-z])[A-Za-z][A-Za-z0-9]*$")
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\\W_]).{8,16}$")
    private String password;
    @NotBlank
    private String passwordConfirmation;
    @NotBlank
    private String name;
    @NotNull
    private Gender gender;
    @Email
    @NotBlank
    private String email;
}
