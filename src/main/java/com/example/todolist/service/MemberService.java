package com.example.todolist.service;

import com.example.todolist.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Member save(Member newMember);

    public List<Member> findMembers() ;

    public Optional<Member> findMember(Long memberId);

    public Optional<Member> findMemberByUsername(String username) ;

    public void deleteMember(Long memberId) ;

    public Member updateMember(Long memoId, Member updatedMember) ;
}
