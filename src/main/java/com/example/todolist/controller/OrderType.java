package com.example.todolist.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderType {
    CreatedAsc, CreatedDsc, DTimeAsc, DTimeDsc;

}
