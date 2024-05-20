package com.example.todolist.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Memo {
    private Long id;
    private String memo;
    private LocalDateTime created;

    @JsonProperty("dTime")
    @JsonFormat
    private LocalDateTime dTime;


}
