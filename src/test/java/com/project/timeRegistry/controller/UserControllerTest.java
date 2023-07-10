package com.project.timeRegistry.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.mapper.UserMapper;
import com.project.timeRegistry.service.impl.UserService;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.project.timeRegistry.Path.PATH_USER;
import static com.project.timeRegistry.Path.STATUS;
import static com.project.timeRegistry.model.factory.UserFactoryTest.createValidUser;
import static com.project.timeRegistry.model.factory.UserRequestFactoryTest.createInvalidUserRequest;
import static com.project.timeRegistry.model.factory.UserRequestFactoryTest.createValidUserRequest;
import static com.project.timeRegistry.model.mapper.UserMapper.USER_MAPPER;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    public static final Long ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private final UserMapper userMapper = USER_MAPPER;


    @Test
    void shouldReturn200whenGetByIdIsSuccess() throws Exception {
        //given
        var user = createValidUser();

        //when
        when(userService.getById(ID)).thenReturn(user);
        final var userResponse = this.userMapper.userToUserResponse(user);

        //then
        this.mockMvc.perform(get(PATH_USER + "/" + user.getId().toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.login").value(user.getLogin()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));

        verify(this.userService, times(1)).getById(ID);
    }

    @Test
    void shouldReturn404whenGetByIdIsNotFound() throws Exception {
        //when
        when(userService.getById(ID)).thenThrow(ObjectNotFoundException.class);

        //then
        this.mockMvc.perform(get(PATH_USER + "/" + ID))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

        verify(this.userService, times(1)).getById(ID);
    }

    @Test
    void shouldReturn201whenCreateUserIsSuccess() throws Exception {
        //given
        var userRequest = createValidUserRequest();
        var user = createValidUser();

        //when
        when(userService.create(any(User.class))).thenReturn(user);

        //then
        this.mockMvc.perform(post(PATH_USER)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**/" + PATH_USER + "/" + user.getId()))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.login").value(user.getLogin()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));

        verify(this.userService, times(1)).create(any(User.class));
    }

    @Test
    void shouldReturn400whenCreateWithInvalidUserRequest() throws Exception {
        //given
        var userRequest = createInvalidUserRequest();

        //then
        this.mockMvc.perform(post(PATH_USER)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn200whenUpdateUserIsSuccess() throws Exception {
        //given
        var userRequest = createValidUserRequest();
        var user = createValidUser();

        //when
        when(userService.update(eq(ID), any(User.class))).thenReturn(user);

        //then
        this.mockMvc.perform(put(PATH_USER + "/" + user.getId().toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.login").value(user.getLogin()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));

        verify(this.userService, times(1)).update(eq(ID), any(User.class));
    }

    @Test
    void shouldReturn400whenUpdateWithIvalidUserRequest() throws Exception {
        //given
        var userRequest = createInvalidUserRequest();

        //then
        this.mockMvc.perform(put(PATH_USER + "/" + ID)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn404whenUpdateIsNotFound() throws Exception {
        //given
        var userRequest = createValidUserRequest();

        //when
        when(userService.update(eq(ID), any(User.class))).thenThrow(ObjectNotFoundException.class);

        //then
        this.mockMvc.perform(put(PATH_USER + "/" + ID)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

        verify(this.userService, times(1)).update(eq(ID), any(User.class));
    }

    @Test
    void shouldReturn200whenDeactivateUserIsSuccess() throws Exception {
        //given
        var user = createValidUser();

        //when
        when(userService.deactivate(user.getId())).thenReturn(user);

        //then
        this.mockMvc.perform(delete(PATH_USER + "/" + user.getId().toString() + STATUS))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.login").value(user.getLogin()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.status").value(user.getStatus().getDescription()));

        verify(this.userService, times(1)).deactivate(user.getId());
    }

    @Test
    void shouldReturn404whenDeactivateUserIsNotFound() throws Exception {
        //when
        when(userService.deactivate(ID)).thenThrow(ObjectNotFoundException.class);

        //then
        this.mockMvc.perform(delete(PATH_USER + "/" + ID + STATUS))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

        verify(this.userService, times(1)).deactivate(ID);
    }

    @Test
    void shouldReturn200whenActivateUserIsSuccess() throws Exception {
        //given
        var user = createValidUser();

        //when
        when(userService.activate(user.getId())).thenReturn(user);

        //then
        this.mockMvc.perform(post(PATH_USER + "/" + user.getId().toString() + STATUS))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.login").value(user.getLogin()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.status").value(user.getStatus().getDescription()));

        verify(this.userService, times(1)).activate(user.getId());
    }

    @Test
    void shouldReturn404whenActivateUserIsNotFound() throws Exception {
        //when
        when(userService.activate(ID)).thenThrow(ObjectNotFoundException.class);

        //then
        this.mockMvc.perform(delete(PATH_USER + "/" + ID + STATUS))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

        verify(this.userService, times(1)).activate(ID);
    }

}