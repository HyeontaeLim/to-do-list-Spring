package com.example.todolist.repository.memberRepository;

import com.example.todolist.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member memberSave(Member member);

    List<Member> findMemberAll();

    Optional<Member> findById(Long memberId);

    Optional<Member> findByUsername(String username);

    void deleteById(Long memberId);

    Member updateById(Long memberId, Member member);
}
