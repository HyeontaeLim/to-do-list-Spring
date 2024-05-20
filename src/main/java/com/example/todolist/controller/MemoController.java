package com.example.todolist.controller;

import com.example.todolist.domain.Memo;
import com.example.todolist.service.MemoServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemoController {
    private final MemoServiceImpl memoService;

    @GetMapping(value = "/memos", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Memo> getMemoList() {
        return memoService.findMemo();
    }

    @PostMapping("/memos")
    @ResponseBody
    public Memo memoAdd(@RequestBody Memo memo) {

        memoService.addMemo(memo);

        return memo;
    }
}
