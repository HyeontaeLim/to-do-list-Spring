package com.example.todolist.repository;

import com.example.todolist.domain.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Repository
public class MemoryMemoRepository implements MemoRepository{

    private static final Map<Long, Memo> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Memo memoSave(Memo memo) {
        memo.setId(++sequence);
        memo.setCreated(LocalDateTime.now());
        store.put(memo.getId(), memo);
        return memo;
    }

    @Override
    public List<Memo> findMemoAll() {
        List<Memo> memos = store.values().stream().toList();
        return memos;
    }

    @Override
    public Optional<Memo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Memo> findByDate(LocalDateTime from, LocalDateTime to) {
        List<Memo> memoList = store.values().stream().filter(memo -> memo.getCreated().isAfter(from) && memo.getCreated().isBefore(to)).toList();
        return memoList;
    }
}
