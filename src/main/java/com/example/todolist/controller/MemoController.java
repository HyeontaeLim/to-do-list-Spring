package com.example.todolist.controller;

import com.example.todolist.controller.dto.AddMemo;
import com.example.todolist.controller.dto.MemoList;
import com.example.todolist.controller.dto.UpdateMemo;
import com.example.todolist.domain.Memo;
import com.example.todolist.service.MemoService;
import com.example.todolist.service.MemoServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @GetMapping(value = "/memos", produces = "application/json; charset=UTF-8")
    public List<MemoList> getMemoList() {
        List<Memo> memos = memoService.findMemos();
        List<MemoList> memoLists = new ArrayList<>();
        for (Memo memo : memos) {
            MemoList memoList = new MemoList();
            memoList.setId(memo.getId());
            memoList.setMemo(memo.getMemo());
            memoList.setCreated(memo.getCreated());
            memoList.setDTime(memo.getDTime());
            memoLists.add(memoList);
        }
        return memoLists;
    }

    @PostMapping("/memos")
    public void addMemo(@RequestBody AddMemo addMemo) {
        Memo memo = new Memo();
        log.info("addMemo.getMemo() ={}", addMemo.getMemo());
        log.info("addMemo.getDTime() ={}", addMemo.getDTime());
        memo.setMemo(addMemo.getMemo());
        memo.setDTime(addMemo.getDTime());
        memoService.addMemo(memo);
    }

    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable ("id") Long id) {
        memoService.deleteMemo(id);
    }

    @PutMapping("/memos/{id}")
    public void updateMemo(@RequestBody UpdateMemo updateMemo, @PathVariable ("id") Long id) {
        Memo memo = new Memo();
        log.info("updateMemo.getMemo() ={}", updateMemo.getMemo());
        log.info("updateMemo.getDTime() ={}", updateMemo.getDTime());
        memo.setMemo(updateMemo.getMemo());
        memo.setDTime(updateMemo.getDTime());
        memoService.updateMemo(id, memo);
    }
}
