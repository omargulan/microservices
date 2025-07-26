package com.example.posts.controller;

import com.example.posts.PostRepository;
import com.example.posts.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final RestTemplate restTemplate = new RestTemplate();
        @Value("${users.server.url}")
    private String usersServerUrl;
//    private final Map<Long, Post> posts = new HashMap<>();
//    private long nextId;
    private final PostRepository postRepository;

    @PostMapping
    public Post create(@RequestBody Post post){
        String usl = usersServerUrl + "/" + post.getAuthorId();
        restTemplate.getForObject(usl, Object.class);
        //post.setId(++nextId);
        //post.setDescription(post.getDescription());
        //post.setAuthorId();

        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return post;
    }

    @GetMapping("/{postId}")
    public Post findById(@PathVariable long postId){
        return postRepository.findById(postId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
