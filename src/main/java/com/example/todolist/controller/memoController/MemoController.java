package com.example.todolist.controller.memoController;

import com.example.todolist.controller.memoController.dto.AddUpdateMemoForm;
import com.example.todolist.controller.memoController.dto.MemoForm;
import com.example.todolist.controller.errorDto.FieldErrorDetail;
import com.example.todolist.controller.errorDto.ValidationResult;
import com.example.todolist.domain.memo.Memo;
import com.example.todolist.domain.memo.OrderType;
import com.example.todolist.service.MemoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;
    private final MessageSource messageSource;

    @GetMapping(value = "/memos", produces = "application/json; charset=UTF-8")
    public List<MemoForm> getMemoList(@RequestParam (name = "orderType", required = false) OrderType orderType) {
        List<Memo> memos = memoService.findMemos();
        List<MemoForm> memoForms = new ArrayList<>();
        for (Memo memo : memos) {
            MemoForm memoForm = new MemoForm();
            memoForm.setMemoId(memo.getMemoId());
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

    @PostMapping(value = "/memos", produces = "application/json; charset=UTF-8")
    public Object addMemo(@Validated @RequestBody AddUpdateMemoForm addUpdateMemoForm, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            log.info("검즘 오류 발생 errors = {}", bindingResult);
            return new ValidationResult(bindingResult, response, messageSource);
        }
        Memo memo = new Memo();
        log.info("addMemo.getMemo()={}", addUpdateMemoForm.getMemo());
        log.info("addMemo.getDTime()={}", addUpdateMemoForm.getDTime());
        memo.setMemo(addUpdateMemoForm.getMemo());
        memo.setDTime(addUpdateMemoForm.getDTime());
        memo.setCreated(LocalDateTime.now());
        return memoService.addMemo(memo);
    }

    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(@PathVariable ("memoId") Long memoId) {
        memoService.deleteMemo(memoId);
    }

    @PutMapping(value = "/memos/{memoId}", produces = "application/json; charset=UTF-8")
    public Object updateMemo(@Validated @RequestBody AddUpdateMemoForm addUpdateMemoForm, BindingResult bindingResult, @PathVariable ("memoId") Long memoId, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            log.info("검즘 오류 발생 errors = {}", bindingResult);
            return new ValidationResult(bindingResult, response, messageSource);
        }

        Memo memo = new Memo();
        log.info("updateMemo.getMemo()={}", addUpdateMemoForm.getMemo());
        log.info("updateMemo.getDTime()={}", addUpdateMemoForm.getDTime());
        memo.setMemo(addUpdateMemoForm.getMemo());
        memo.setDTime(addUpdateMemoForm.getDTime());
        return memoService.updateMemo(memoId, memo);

    }
}
