package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    //private final Map<Long, User> users = new HashMap<>();
    //private long nextId;
    private final UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user){
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
