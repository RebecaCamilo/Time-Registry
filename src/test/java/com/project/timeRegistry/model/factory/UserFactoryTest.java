package com.project.timeRegistry.model.factory;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.domain.WorkedTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class UserFactoryTest {

    public static User createValidUser() {
        return User.builder()
                .id(1L)
                .name("name")
                .login("login")
                .password("pass")
                .build();
    }
}
