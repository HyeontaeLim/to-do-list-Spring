package com.example.todolist.service;

import com.example.todolist.domain.Memo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemoService {
   Memo addMemo(Memo newMemo);
   List<Memo> findMemos();

   Optional<Memo> findMemo(Long id);
   void deleteMemo(Long id);
   Memo updateMemo(Long id, Memo updatedMemo);
}
