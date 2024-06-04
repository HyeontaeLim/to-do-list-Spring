package com.example.todolist.controller;

import com.example.todolist.controller.dto.AddUpdateMemoForm;
import com.example.todolist.controller.dto.MemoForm;
import com.example.todolist.domain.Memo;
import com.example.todolist.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @GetMapping(value = "/memos", produces = "application/json; charset=UTF-8")
    public List<MemoForm> getMemoList(@RequestParam (name = "orderType", required = false) OrderType orderType) {
        List<Memo> memos = memoService.findMemos();
        List<MemoForm> memoForms = new ArrayList<>();
        for (Memo memo : memos) {
            MemoForm memoForm = new MemoForm();
            memoForm.setId(memo.getId());
            memoForm.setMemo(memo.getMemo());
            memoForm.setCreated(memo.getCreated());
            memoForm.setDTime(memo.getDTime());
            memoForms.add(memoForm);
        }
        if (OrderType.CreatedAsc.equals(orderType)) {
            memoForms.sort(Comparator.comparing(MemoForm::getCreated));
            return memoForms.reversed();
        } else if (OrderType.CreatedDsc.equals(orderType)) {
            memoForms.sort(Comparator.comparing(MemoForm::getCreated));
            return memoForms;
        } else if (OrderType.DTimeAsc.equals(orderType)) {
            memoForms.sort(Comparator.comparing(MemoForm::getDTime));
            return memoForms.reversed();
        } else if (OrderType.DTimeDsc.equals(orderType)) {
            memoForms.sort(Comparator.comparing(MemoForm::getDTime));
            return memoForms;
        }
        return memoForms;
    }

    @PostMapping("/memos")
    public void addMemo(@RequestBody AddUpdateMemoForm addUpdateMemoForm) {
        Memo memo = new Memo();
        log.info("addMemo.getMemo()={}", addUpdateMemoForm.getMemo());
        log.info("addMemo.getDTime()={}", addUpdateMemoForm.getDTime());
        memo.setMemo(addUpdateMemoForm.getMemo());
        memo.setDTime(addUpdateMemoForm.getDTime());
        memoService.addMemo(memo);
    }

    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable ("id") Long id) {
        memoService.deleteMemo(id);
    }

    @PutMapping("/memos/{id}")
    public void updateMemo(@RequestBody AddUpdateMemoForm addUpdateMemoForm, @PathVariable ("id") Long id) {
        Memo memo = new Memo();
        log.info("updateMemo.getMemo()={}", addUpdateMemoForm.getMemo());
        log.info("updateMemo.getDTime()={}", addUpdateMemoForm.getDTime());
        memo.setMemo(addUpdateMemoForm.getMemo());
        memo.setDTime(addUpdateMemoForm.getDTime());
        memoService.updateMemo(id, memo);
    }
}
