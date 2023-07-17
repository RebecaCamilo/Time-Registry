package com.project.timeRegistry.model.mapper;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.request.UserRequest;
import org.junit.jupiter.api.Test;

import static com.project.timeRegistry.model.factory.UserFactoryTest.createValidUser;
import static com.project.timeRegistry.model.factory.UserRequestFactoryTest.createValidUserRequest;
import static com.project.timeRegistry.model.mapper.UserMapper.USER_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    @Test
    public void shouldMapUserRequestToUser() {
        //given
        UserRequest userRequest = createValidUserRequest();

        //when
        var result = USER_MAPPER.userRequestToUser(userRequest);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getNickname()).isEqualTo(userRequest.getName());
        assertThat(result.getLogin()).isEqualTo(userRequest.getLogin());
        assertThat(result.getPassword()).isEqualTo(userRequest.getPassword());
    }

    @Test
    public void shouldMapNullWhenUserRequestIsNull() {
        //when
        var result = USER_MAPPER.userRequestToUser(null);

        //then
        assertThat(result).isNull();
    }

    @Test
    public void shouldMapUserToUserResponse() {
        //given
        User user = createValidUser();

        //when
        var result = USER_MAPPER.userToUserResponse(user);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(user.getNickname());
        assertThat(result.getLogin()).isEqualTo(user.getLogin());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
        assertThat(result.getStatus()).isEqualTo(user.getStatus().getDescription());
    }

    @Test
    public void shouldMapNullWhenUserIsNull() {
        //when
        var result = USER_MAPPER.userToUserResponse(null);

        //then
        assertThat(result).isNull();
    }

}