package com.example.todolist.service;

import com.example.todolist.domain.member.Member;
import com.example.todolist.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public String hashPassword(String password) {
       return passwordEncoder.encode(password);
    }

    @Override
    public Member login(String username, String password) {
        return memberRepository.findByUsername(username)
                .filter(member -> passwordEncoder.matches(password, member.getPassword()))
                .orElse(null);
    }
}
