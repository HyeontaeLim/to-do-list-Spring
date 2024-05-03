package com.example.todolist.repository;

import com.example.todolist.domain.Memo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemoRepository implements MemoRepository{

    private static List<Memo> store = new ArrayList<>();

    @Override
    public Memo memoSave(Memo memo) {
        store.add(memo);
        return memo;
    }

    @Override
    public List<Memo> findMemoAll() {
        return store;
    }
}
