package com.example.todolist.service;

import com.example.todolist.domain.member.Member;

public interface AuthService {
    String hashPassword(String password);
    Member login(String username, String password);
}
