package com.project.timeRegistry.repository;

import com.project.timeRegistry.model.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.project.timeRegistry.model.factory.UserFactoryTest.createValidUser;

@DataJpaTest
class UserRepositoryTest {

    public static final String LOGIN = "anyLogin";
    private final UserRepository userRepository;
//	private final Pageable defaultPageable;

    public UserRepositoryTest(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
//		this.defaultPageable = Pageable.defaultFirstPage();
    }

    @Test
    void injected_components_are_NotNull() {
        Assertions.assertThat(userRepository).isNotNull();
    }

    @Test
    void shouldCountByLoginReturn1WhenCountAUserWithSameLogin() {
        //given
        var user = createValidUser();

        //when
        var savedUser = userRepository.save(user);
        var result = userRepository.countByLogin(savedUser.getLogin());

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isExactlyInstanceOf(Integer.class);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldCountByLoginReturn0WhenCountNoUserWithSameLogin() {
        //when
        var result = userRepository.countByLogin(LOGIN);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isExactlyInstanceOf(Integer.class);
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldGetByLoginReturnOptionalOfUserWhenUserExistsByLogin() {
        //given
        var user = createValidUser();

        //when
        var savedUser = userRepository.save(user);
        var result = userRepository.getByLogin(savedUser.getLogin());

        //then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.get()).isExactlyInstanceOf(User.class);
        Assertions.assertThat(result.get()).isEqualTo(savedUser);
    }

    @Test
    void shouldGetByLoginReturnEmptyWhenUserNotExistsByLogin() {
        //when
        var result = userRepository.getByLogin(LOGIN);

        //then
        Assertions.assertThat(result).isEmpty();
        Assertions.assertThat(result).isExactlyInstanceOf(Optional.class);
    }
}