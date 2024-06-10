package com.example.todolist.repository;

import com.example.todolist.domain.Memo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemoRepository {
    Memo memoSave(Memo memo);
    List<Memo> findMemoAll();

    Optional<Memo> findById(Long memoId);

    void deleteById(Long memoId);

    Memo updateById(Long memoId, Memo memo);

    // List<Memo> findByDate(LocalDateTime from, LocalDateTime to);
}
