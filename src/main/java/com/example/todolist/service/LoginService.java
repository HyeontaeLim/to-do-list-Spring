package com.example.todolist.service;

import com.example.todolist.domain.member.Member;
import com.example.todolist.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String username, String password) {
        return memberRepository.findByUsername(username)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }


}
