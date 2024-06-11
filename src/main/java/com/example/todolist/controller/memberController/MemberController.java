package com.example.todolist.controller.memberController;

import com.example.todolist.controller.memberController.dto.AddUpdateMemberForm;
import com.example.todolist.domain.member.Member;
import com.example.todolist.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @PostMapping(value = "/members", produces = "application/json; charset=UTF-8")
    Member resgistMember(@RequestBody AddUpdateMemberForm addUpdateMemberForm) {
        Member member = new Member();
        member.setName(addUpdateMemberForm.getName());
        member.setPassword(addUpdateMemberForm.getPassword());
        member.setEmail(addUpdateMemberForm.getEmail());
        member.setUsername(addUpdateMemberForm.getUsername());
        member.setGender(addUpdateMemberForm.getGender());
        return memberRepository.memberSave(member);
    }
}
