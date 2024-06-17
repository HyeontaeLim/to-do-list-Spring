package com.example.todolist.repository.memoRepository;

import com.example.todolist.domain.memo.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {
    Memo memoSave(Memo memo);

    List<Memo> findMemoAll();

    List<Memo> findMemoByMemberId(Long memberId);

    Optional<Memo> findById(Long memoId);

    void deleteById(Long memoId);

    Memo updateById(Long memoId, Memo memo);

    // List<Memo> findByDate(LocalDateTime from, LocalDateTime to);
}
