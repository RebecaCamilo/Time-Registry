package com.project.timeRegistry.controller;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.timeRegistry.Path.*;

@RestController(value = PATH_USER)
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<User> getAll(@PathVariable Long id) {
        return null;
    }

    @GetMapping(ID)
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<User> create(@PathVariable Long id) {
        return null;
    }

    @PutMapping(ID)
    public ResponseEntity<User> update(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping(ID + STATUS)
    public ResponseEntity<User> deactivate(@PathVariable Long id) {
        return null;
    }

    @PostMapping(ID + STATUS)
    public ResponseEntity<User> activate(@PathVariable Long id) {
        return null;
    }
}
