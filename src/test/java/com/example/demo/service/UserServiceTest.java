package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void shouldReturnInitialUsers() {
        List<User> users = userService.findAll();
        assertEquals(2, users.size());
        assertEquals("张三", users.get(0).name());
    }

    @Test
    void shouldCreateUser() {
        User user = userService.create(new UserCreateRequest("王五", "wangwu@example.com"));
        assertNotNull(user.id());
        assertEquals("王五", userService.findById(user.id()).name());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> userService.findById(999L));
    }
}
