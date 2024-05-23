package com.example.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class MemoList implements Comparable<MemoList> {
    private Long id;
    private String memo;
    private LocalDateTime created;
    @JsonProperty("dTime")
    @JsonFormat
    private LocalDateTime dTime;

    @Override
    public int compareTo(MemoList o) {
        return 0;
    }
}
