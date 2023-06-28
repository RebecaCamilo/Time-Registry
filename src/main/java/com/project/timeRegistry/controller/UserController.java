package com.project.timeRegistry.controller;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return null;
    }
}
