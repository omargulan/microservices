package com.example.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;


@RestController

@RequestMapping("/posts")
public class PostController {

    private final RestTemplate restTemplate;

    public PostController(RestTemplateBuilder builder, @Value("${posts.server.url}") String url){
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return restTemplate.getForEntity("/posts", Object.class);
    }

    @GetMapping("/{postsId}")
    public ResponseEntity<Object> findById(@PathVariable long postsId){
        return restTemplate.getForEntity("/posts/{postId}", Object.class, postsId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Object requestObj){
        return restTemplate.postForEntity("/posts", requestObj, Object.class);
    }
}
