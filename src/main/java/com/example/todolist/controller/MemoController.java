package com.example.todolist.controller;

import com.example.todolist.domain.Memo;
import com.example.todolist.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
public class MemoController {
    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping(value = "/memos", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Memo> getMemoList() {
        return memoService.findMemo();
    }

    @PostMapping("/memos")
    @ResponseBody
    public Memo memoAdd(@RequestBody Memo memo) {
        Memo memo1 = new Memo();
        memo1.setMemo(memo.getMemo());
        System.out.println(memo1.getMemo());
        memoService.addMemo(memo1);
        return memo1;
    }
}
