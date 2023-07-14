package com.project.timeRegistry.controller;

import com.project.timeRegistry.exception.StandardError;
import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.request.UserRequest;
import com.project.timeRegistry.model.response.UserResponse;
import com.project.timeRegistry.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.project.timeRegistry.Path.*;
import static com.project.timeRegistry.model.mapper.UserMapper.USER_MAPPER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = PATH_USER, produces = APPLICATION_JSON_VALUE)
@Tag(name = "User Controller")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(ID)
    @Operation(summary = "Find a user by its identifier", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Find a user by its identifier with success"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found by its identifier",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = InternalError.class))
            )
    })
    public ResponseEntity<UserResponse> getById(
            @Parameter(description = "User id", example = "1")
            @PathVariable Long id
    ) {
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));

    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Register a user by a UserRequest", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Register a user with success"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "UserRequest invalid",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Login already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = InternalError.class))
            )
    })
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody UserRequest request
    ) {
        User user = userService.create(USER_MAPPER.userRequestToUser(request));
        UserResponse userResponse = USER_MAPPER.userToUserResponse(user);
        final var uri = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @PutMapping(name = ID, consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a user by its identifier and a UserRequest", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Update a user with success"),
            @ApiResponse(
                    responseCode = "400",
                    description = "UserRequest invalid",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found by its identifier",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Login already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = InternalError.class))
            )
    })
    public ResponseEntity<UserResponse> update(
            @Valid @RequestBody UserRequest request,
            @Parameter(description = "User id", example = "1")
            @PathVariable Long id
    ) {
        User user = userService.update(id, USER_MAPPER.userRequestToUser(request));
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));
    }

    @DeleteMapping(ID + STATUS)
    @Operation(summary = "Deactivate a user's status by its identifier", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Set user's status to inactive with success"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found by its identifier",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = InternalError.class))
            )
    })
    public ResponseEntity<UserResponse> deactivate(
            @Parameter(description = "User id", example = "1")
            @PathVariable Long id
    ) {
        User user = userService.deactivate(id);
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));
    }

    @PostMapping(ID + STATUS)
    @Operation(summary = "Activate a user's status by its identifier", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Set user's status to active with success"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found by its identifier",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = InternalError.class))
            )
    })
    public ResponseEntity<UserResponse> activate(
            @Parameter(description = "User id", example = "1")
            @PathVariable Long id
    ) {
        User user = userService.activate(id);
        return ResponseEntity.status(HttpStatus.OK).body(USER_MAPPER.userToUserResponse(user));
    }
}
