package com.example.todolist.service;

import com.example.todolist.domain.member.Member;
import com.example.todolist.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member save(Member newMember) {
        return memberRepository.memberSave(newMember);
    }

    @Override
    public List<Member> findMemos() {
        return memberRepository.findMemberAll();
    }

    @Override
    public Optional<Member> findMemo(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void deleteMemo(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public Member updateMemo(Long memoId, Member updatedMember) {
        return memberRepository.updateById(memoId, updatedMember);
    }
}
