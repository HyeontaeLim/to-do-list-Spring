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
public class AddMemo {
    private String memo;

    @JsonProperty("dTime")
    @JsonFormat
    private LocalDateTime dTime;
}
