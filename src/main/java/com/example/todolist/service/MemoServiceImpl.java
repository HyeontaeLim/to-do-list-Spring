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
        return memoRepository.memoSave(newMemo);
    }

    public List<Memo> findMemos() {
        return memoRepository.findMemoAll();
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

    public Memo updateMemo(Long id, Memo updatedMemo) {
        return memoRepository.updateById(id, updatedMemo);
    }
}
