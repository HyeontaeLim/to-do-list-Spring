package com.example.todolist.service;

import com.example.todolist.domain.Memo;
import com.example.todolist.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService{
    private final MemoRepository memoRepository;

    public Memo addMemo(Memo newMemo) {
        Memo createdMemo = memoRepository.memoSave(newMemo);
        return createdMemo;
    }

    public List<Memo> findMemo() {
        return memoRepository.findMemoAll();
    }
}
