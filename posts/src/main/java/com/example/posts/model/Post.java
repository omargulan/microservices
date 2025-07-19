package com.example.posts.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@RequiredArgsConstructor
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(nullable = false)
    private Long authorId;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
