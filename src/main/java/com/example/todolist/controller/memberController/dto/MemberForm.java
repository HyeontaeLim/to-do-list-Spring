package com.example.todolist.controller.memberController.dto;

import com.example.todolist.domain.member.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberForm {
    @NotBlank
    private Long memberId;
    @NotBlank
    @
    private String username;
    @Size(min = 8)
    @
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotNull
    private Gender gender;
    @Email
    @NotBlank
    private String email;
}
