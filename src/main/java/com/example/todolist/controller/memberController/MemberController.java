package com.example.todolist.controller.memberController;

import com.example.todolist.controller.errorDto.ValidationResult;
import com.example.todolist.controller.memberController.dto.AddUpdateMemberForm;
import com.example.todolist.domain.member.Member;
import com.example.todolist.repository.memberRepository.MemberRepository;
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
@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    private final MessageSource messageSource;

    @PostMapping(value = "/members", produces = "application/json; charset=UTF-8")
    Object registerMember(@RequestBody @Validated AddUpdateMemberForm addUpdateMemberForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return new ValidationResult(bindingResult, response, messageSource);
        }

        Member member = new Member();
        member.setName(addUpdateMemberForm.getName());
        member.setPassword(addUpdateMemberForm.getPassword());
        member.setEmail(addUpdateMemberForm.getEmail());
        member.setUsername(addUpdateMemberForm.getUsername());
        member.setGender(addUpdateMemberForm.getGender());
        return memberRepository.memberSave(member);
    }
}
