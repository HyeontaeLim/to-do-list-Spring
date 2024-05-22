package com.example.todolist.repository;

import com.example.todolist.domain.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


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
        return new ArrayList<Memo>(store.values());
    }

    @Override
    public Optional<Memo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Memo updateById(Long id, Memo memo) {
        return null;
    }

/*    @Override
    public List<Memo> findByDate(LocalDateTime from, LocalDateTime to) {
        List<Memo> memos = findMemoAll();
        List<Memo> memoList = new ArrayList<>();
        for (Memo memo : memos) {
            if (memo.getDTime().isAfter(from) && memo.getDTime().isBefore(to)) {
                memoList.add(memo);
            }
        }
        return memoList;
    }
    */
}
