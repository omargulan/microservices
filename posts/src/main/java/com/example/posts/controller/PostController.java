package com.example.posts.controller;

import com.example.posts.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${users.server.url}")
    private String usersServerUrl;
    private final Map<Long, Post> posts = new HashMap<>();
    private long nextId;

    @PostMapping
    public Post create(@RequestBody Post post){
        String usl = usersServerUrl + "/" + post.getAuthorId();
        restTemplate.getForObject(usl, Object.class);
        post.setId(++nextId);
        post.setDescription(post.getDescription());
        //post.setAuthorId();
        post.setCreatedAt(LocalDateTime.now());
        posts.put(post.getId(), post);
        return post;
    }

    @GetMapping("/{postId}")
    public Post findById(@PathVariable long postId){
        return posts.get(postId);
    }

    @GetMapping
    public List<Post> findAll(){
        return List.copyOf(posts.values());
    }
}
