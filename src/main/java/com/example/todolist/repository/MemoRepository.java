package com.example.todolist.repository;

import com.example.todolist.domain.Memo;

import java.util.List;

public interface MemoRepository {
    Memo memoSave(Memo memo);
    List<Memo> findMemoAll();
}
