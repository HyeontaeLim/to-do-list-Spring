package com.example.todolist.domain.memo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderType {
    CreatedAsc, CreatedDsc, DTimeAsc, DTimeDsc;

}
