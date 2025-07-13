package com.example.posts.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Long id;
    private String description;
    private Long authorId;
    private LocalDateTime createdAt;
}
