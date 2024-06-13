package com.example.todolist.service;

import com.example.todolist.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Member save(Member newMember);

    public List<Member> findMemos() ;

    public Optional<Member> findMemo(Long memberId) ;

    public void deleteMemo(Long memberId) ;

    public Member updateMemo(Long memoId, Member updatedMember) ;
}
