package com.example.todolist.service;

import com.example.todolist.domain.memo.Memo;
import com.example.todolist.repository.memoRepository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService{
    private final MemoRepository memoRepository;

    public Memo addMemo(Memo newMemo) {
        return memoRepository.memoSave(newMemo);
    }

    public List<Memo> findMemos(Long memberId) {
        return memoRepository.findMemoByMemberId(memberId);
    }

    public Optional<Memo> findMemo(Long memoId) {
        return memoRepository.findById(memoId);
    }

    public void deleteMemo(Long memoId) {
        memoRepository.deleteById(memoId);
    }

    public Memo updateMemo(Long memoId, Memo updatedMemo) {
        return memoRepository.updateById(memoId, updatedMemo);
    }
}
