package com.project.timeRegistry.model.factory;

import com.project.timeRegistry.model.domain.User;

import static com.project.timeRegistry.model.domain.UserStatus.ACTIVE;

public class UserFactoryTest {

    public static User createValidUser() {
        return User.builder()
                .id(1L)
                .name("name")
                .login("login")
                .password("pass")
                .status(ACTIVE)
                .build();
    }
}
