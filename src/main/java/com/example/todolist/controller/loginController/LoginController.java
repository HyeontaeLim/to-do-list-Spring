package com.example.todolist.controller.loginController;

import com.example.todolist.SessionConst;
import com.example.todolist.controller.errorDto.ValidationResult;
import com.example.todolist.controller.loginController.dto.LoginForm;
import com.example.todolist.domain.member.Member;
import com.example.todolist.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthService authService;
    private final MessageSource messageSource;

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public Object login(@RequestBody @Validated LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) {
        Member loginMember = authService.login(loginForm.getUsername(), loginForm.getPassword());
        if (loginMember == null) {
            bindingResult.reject("MemberNotFound");
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return new ValidationResult(bindingResult, response, messageSource);
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션은 생성
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*60*3);
        //세션에 로그인 회원 정보를 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getMemberId());
        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)

        return null;
    }
}
