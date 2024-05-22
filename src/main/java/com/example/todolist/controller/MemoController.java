package com.example.todolist.controller;

import com.example.todolist.domain.Memo;
import com.example.todolist.service.MemoService;
import com.example.todolist.service.MemoServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @GetMapping(value = "/memos", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Memo> getMemoList() {
        return memoService.findMemos();
    }

    @PostMapping("/memos")
    public Memo memoAdd(@RequestBody Memo memo) {
        return memoService.addMemo(memo);
    }

    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
    }

    @PutMapping("/memos/{id}")
    public Memo updateMemo(@RequestBody Memo memo, @PathVariable Long id) {
        return memoService.updateMemo(id, memo);
    }
}
