package com.example.users.controller;

import com.example.users.model.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Long, User> users = new HashMap<>();
    private long nextId;

    @PostMapping
    public User create(@RequestBody User user){
        user.setId(++nextId);
        user.setCreatedAt(LocalDateTime.now());
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId){
        return users.get(userId);
    }

    @GetMapping
    public List<User> findAll(){
        return List.copyOf(users.values());
    }

}
