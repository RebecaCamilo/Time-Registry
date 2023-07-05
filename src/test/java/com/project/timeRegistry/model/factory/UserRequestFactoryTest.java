package com.project.timeRegistry.model.factory;

import com.project.timeRegistry.model.request.UserRequest;

public class UserRequestFactoryTest {

    public static UserRequest createValidUserRequest() {
        return UserRequest.builder()
                .name("name")
                .login("login")
                .password("pass")
                .build();
    }
}
