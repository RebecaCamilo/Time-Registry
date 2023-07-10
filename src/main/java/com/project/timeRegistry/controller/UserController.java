package com.project.timeRegistry.controller;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.request.UserRequest;
import com.project.timeRegistry.model.response.UserResponse;
import com.project.timeRegistry.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.project.timeRegistry.Path.*;
import static com.project.timeRegistry.model.mapper.UserMapper.USER_MAPPER;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(PATH_USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(ID)
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));

    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        User user = userService.create(USER_MAPPER.userRequestToUser(request));
        UserResponse userResponse = USER_MAPPER.userToUserResponse(user);
        final var uri = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @PutMapping(ID)
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest request, @PathVariable Long id) {
        User user = userService.update(id, USER_MAPPER.userRequestToUser(request));
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));
    }

    @DeleteMapping(ID + STATUS)
    public ResponseEntity<UserResponse> deactivate(@PathVariable Long id) {
        User user = userService.deactivate(id);
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));
    }

    @PostMapping(ID + STATUS)
    public ResponseEntity<UserResponse> activate(@PathVariable Long id) {
        User user = userService.activate(id);
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));
    }
}
