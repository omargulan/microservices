package com.example.users.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String login;
    private LocalDateTime createdAt;
}
