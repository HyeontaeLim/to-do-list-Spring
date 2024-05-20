package com.example.todolist.service;

import com.example.todolist.domain.Memo;

import java.time.LocalDateTime;
import java.util.List;

public interface MemoService {
   Memo addMemo(Memo newMemo);
   List<Memo> findMemo();
}
