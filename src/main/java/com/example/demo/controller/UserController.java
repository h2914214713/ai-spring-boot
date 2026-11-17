package com.example.demo.controller;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * 用户接口，演示三个基础 REST API。
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 接口 1：查询用户列表。
     * GET /api/users
     */
    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }

    /**
     * 接口 2：按 ID 查询用户详情。
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public User detail(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * 接口 3：创建用户。
     * POST /api/users
     */
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserCreateRequest request,
                                       UriComponentsBuilder uriBuilder) {
        User created = userService.create(request);
        URI location = uriBuilder.path("/api/users/{id}").buildAndExpand(created.id()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(created);
    }
}
