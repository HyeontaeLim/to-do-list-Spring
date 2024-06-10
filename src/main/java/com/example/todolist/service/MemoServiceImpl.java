package com.example.todolist.service;

import com.example.todolist.domain.Memo;
import com.example.todolist.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Memo> findMemo(Long id) {
        return memoRepository.findById(id);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

    public Memo updateMemo(Long id, Memo updatedMemo) {
        return memoRepository.updateById(id, updatedMemo);
    }
}
