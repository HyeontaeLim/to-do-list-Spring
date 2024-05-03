package com.example.todolist.service;

import com.example.todolist.domain.Memo;
import com.example.todolist.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo addMemo(Memo newMemo) {
        Memo createdMemo = memoRepository.memoSave(newMemo);
        return createdMemo;
    }

    public List<Memo> findMemo() {
        return memoRepository.findMemoAll();
    }
}
