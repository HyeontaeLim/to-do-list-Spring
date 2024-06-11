package com.example.todolist.service;

import com.example.todolist.domain.memo.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoService {
   Memo addMemo(Memo newMemo);
   List<Memo> findMemos();

   Optional<Memo> findMemo(Long memoId);
   void deleteMemo(Long memoId);
   Memo updateMemo(Long memoId, Memo updatedMemo);
}
