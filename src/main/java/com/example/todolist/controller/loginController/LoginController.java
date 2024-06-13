package com.example.todolist.controller.loginController;

import com.example.todolist.controller.errorDto.ValidationResult;
import com.example.todolist.controller.loginController.dto.LoginForm;
import com.example.todolist.domain.member.Member;
import com.example.todolist.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private final MessageSource messageSource;

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public Object login(@RequestBody @Validated LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {
        Member loginMember = loginService.login(loginForm.getUsername(), loginForm.getPassword());
        if (loginMember == null) {
            bindingResult.reject("MemberNotFound");
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return new ValidationResult(bindingResult, response, messageSource);
        }
        return loginMember;
    }
}
