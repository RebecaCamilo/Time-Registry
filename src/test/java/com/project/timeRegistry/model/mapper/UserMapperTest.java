package com.project.timeRegistry.model.mapper;

import com.project.timeRegistry.model.request.UserRequest;
import org.junit.jupiter.api.Test;

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
        assertThat(result.getName()).isEqualTo(userRequest.getName());
        assertThat(result.getLogin()).isEqualTo(userRequest.getLogin());
        assertThat(result.getPassword()).isEqualTo(userRequest.getPassword());
    }

}