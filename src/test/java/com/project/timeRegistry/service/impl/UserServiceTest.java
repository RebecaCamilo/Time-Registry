package com.project.timeRegistry.service.impl;

import com.project.timeRegistry.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.project.timeRegistry.factory.UserFactoryTest.createValidUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private final Long ID = 1L;

    private final UserRepository userRepository;
    private final UserService userService;

    UserServiceTest() {
        this.userRepository = mock(UserRepository.class);
        this.userService = new UserService(this.userRepository);
    }

    @Test
    @DisplayName("should getById return a User when id is valid")
    void shouldGetByIdReturnAUserWhenIdIsValid() {

        // Given
        var user = createValidUser();
        var id = user.getId();

        // When
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        var result = this.userService.getById(id);

        // Then
        assertThat(result).isEqualTo(user);
    }

    @Test
    @DisplayName("should getById thrown ObjectNotFoundException when id is invalid")
    void shouldGetByIdThrownObjectNotFoundExceptionWhenIdIsInvalid() {

        // When
        when(userRepository.findById(ID)).thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> this.userService.getById(ID))
                .isExactlyInstanceOf(ObjectNotFoundException.class);
    }

}