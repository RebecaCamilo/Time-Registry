package com.project.timeRegistry.service.impl;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.domain.UserStatus;
import com.project.timeRegistry.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static com.project.timeRegistry.model.domain.UserStatus.ACTIVE;
import static com.project.timeRegistry.model.domain.UserStatus.INACTIVE;
import static com.project.timeRegistry.model.factory.UserFactoryTest.createValidUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    public static final long ID = 1L;
    private final UserService userService;
    private final UserRepository userRepository;
//    private final Pageable defaultPageable;

    UserServiceTest() {
        this.userRepository = mock(UserRepository.class);
        this.userService = new UserService(this.userRepository);
//        this.defaultPageable = Pageable.defaultFirstPage();
    }


    @Test
    void shouldGetByIdReturnUser() {
        //given
        var user = createValidUser();

        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        final var result = this.userService.getById(user.getId());

        //then
        assertThat(result).isNotNull();
        assertThat(result).isExactlyInstanceOf(User.class);
        assertThat(result).isEqualTo(user);
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getNickname()).isEqualTo(user.getNickname());
        assertThat(result.getLogin()).isEqualTo(user.getLogin());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
        assertThat(result.getStatus()).isExactlyInstanceOf(UserStatus.class);
        assertThat(result.getStatus().getDescription()).isEqualTo(user.getStatus().getDescription());
    }

    @Test
    void shouldGetByIdThrowObjectNotFoundException() {
        //when
        when(this.userRepository.findById(ID)).thenThrow(ObjectNotFoundException.class);

        //then
        assertThatThrownBy(() -> userService.getById(ID))
                .isExactlyInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    void shouldCreateReturnUser() {
        //given
        var user = createValidUser();

        //when
        when(userRepository.countByLogin(user.getLogin())).thenReturn(0);
        when(userRepository.save(user)).thenReturn(user);
        final var result = this.userService.create(user);

        //then
        assertThat(result).isNotNull();
        assertThat(result).isExactlyInstanceOf(User.class);
        assertThat(result).isEqualTo(user);
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getNickname()).isEqualTo(user.getNickname());
        assertThat(result.getLogin()).isEqualTo(user.getLogin());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
        assertThat(result.getStatus()).isExactlyInstanceOf(UserStatus.class);
        assertThat(result.getStatus().getDescription()).isEqualTo(ACTIVE.getDescription());
    }

    @Test
    void shouldCreateThrowDataIntegrityViolationException() {
        //given
        var user = createValidUser();

        //when
        when(userRepository.countByLogin(user.getLogin())).thenReturn(1);

        //then
        assertThatThrownBy(() -> userService.create(user))
                .isExactlyInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldUpdateReturnUser() {
        //given
        var user = createValidUser();

        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.getByLogin(user.getLogin())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        final var result = this.userService.update(user.getId(), user);

        //then
        assertThat(result).isNotNull();
        assertThat(result).isExactlyInstanceOf(User.class);
        assertThat(result).isEqualTo(user);
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getNickname()).isEqualTo(user.getNickname());
        assertThat(result.getLogin()).isEqualTo(user.getLogin());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
        assertThat(result.getStatus()).isExactlyInstanceOf(UserStatus.class);
        assertThat(result.getStatus().getDescription()).isEqualTo(user.getStatus().getDescription());
    }

    @Test
    void shouldUpdateThrowDataIntegrityViolationException() {
        //given
        var user = createValidUser();
        var user2 = createValidUser();
        user2.setId(2L);

        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.getByLogin(user.getLogin())).thenReturn(Optional.of(user2));
        when(userRepository.save(user)).thenReturn(user);

        //then
        assertThatThrownBy(() -> userService.update(user.getId(), user))
                .isExactlyInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void shouldDeactivateReturnUserWithStatusInactive() {
        //given
        var user = createValidUser();

        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        final var result = this.userService.deactivate(user.getId());

        //then
        assertThat(result).isNotNull();
        assertThat(result).isExactlyInstanceOf(User.class);
        assertThat(result).isEqualTo(user);
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getStatus()).isExactlyInstanceOf(UserStatus.class);
        assertThat(result.getStatus().getDescription()).isEqualTo(INACTIVE.getDescription());
    }

    @Test
    void shouldDeactivateThrowObjectNotFoundException() {
        //when
        when(this.userRepository.findById(ID)).thenThrow(ObjectNotFoundException.class);

        //then
        assertThatThrownBy(() -> userService.deactivate(ID))
                .isExactlyInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    void shouldActivateReturnUserWithStatusActive() {
        //given
        var user = createValidUser();
        user.setStatus(INACTIVE);

        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        final var result = this.userService.activate(user.getId());

        //then
        assertThat(result).isNotNull();
        assertThat(result).isExactlyInstanceOf(User.class);
        assertThat(result).isEqualTo(user);
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getStatus()).isExactlyInstanceOf(UserStatus.class);
        assertThat(result.getStatus().getDescription()).isEqualTo(ACTIVE.getDescription());
    }

    @Test
    void shouldActivateThrowObjectNotFoundException() {
        //when
        when(this.userRepository.findById(ID)).thenThrow(ObjectNotFoundException.class);

        //then
        assertThatThrownBy(() -> userService.activate(ID))
                .isExactlyInstanceOf(ObjectNotFoundException.class);
    }
}