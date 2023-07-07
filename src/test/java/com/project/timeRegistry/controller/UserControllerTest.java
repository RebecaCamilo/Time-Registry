package com.project.timeRegistry.controller;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.request.UserRequest;
import com.project.timeRegistry.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.project.timeRegistry.Path.PATH_USER;
import static com.project.timeRegistry.model.factory.UserFactoryTest.createValidUser;
import static com.project.timeRegistry.model.factory.UserRequestFactoryTest.createValidUserRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest
class UserControllerTest {

    private UserController userController;
    private UserService userService;
//    private MockMvc mockMvc;

    UserControllerTest() {
//        this.mockMvc =
        this.userService = mock(UserService.class);
        this.userController = new UserController(this.userService);
    }


    @Test
    void shouldReturn201whenCreateIsSuccess() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deactivate() {
    }

    @Test
    void activate() {
    }
}