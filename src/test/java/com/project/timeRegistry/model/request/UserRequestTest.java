package com.project.timeRegistry.model.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRequestTest {

    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private final Validator validator;

    public UserRequestTest() {
        try (final var validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }
    }

    @Test
    void shouldValidateForAllValidAttributes() {
        final var request = UserRequest.builder()
                .name(NAME)
                .login(LOGIN)
                .password(PASSWORD)
                .build();

        final var violations = this.validator.validate(request);

        assertThat(violations)
                .isNotNull()
                .hasSize(0);
    }

    @ParameterizedTest(name = "for arguments {0}, {1}")
    @MethodSource("provideArguments")
    void shouldNotValidateForAnyInvalidAttribute(UserRequest request, String path) {
        final var violations = this.validator.validate(request);

        assertThat(violations)
                .isNotNull()
                .hasSize(1)
                .anyMatch(v -> v.getPropertyPath().toString().equalsIgnoreCase(path));
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new UserRequest("", LOGIN, PASSWORD), NAME),
                Arguments.of(new UserRequest(" ", LOGIN, PASSWORD), NAME),
                Arguments.of(new UserRequest("\t", LOGIN, PASSWORD), NAME),
                Arguments.of(new UserRequest(null, LOGIN, PASSWORD), NAME),
                Arguments.of(new UserRequest(NAME, "", PASSWORD), LOGIN),
                Arguments.of(new UserRequest(NAME, " ", PASSWORD), LOGIN),
                Arguments.of(new UserRequest(NAME, "\t", PASSWORD), LOGIN),
                Arguments.of(new UserRequest(NAME, null, PASSWORD), LOGIN),
                Arguments.of(new UserRequest(NAME, LOGIN, ""), PASSWORD),
                Arguments.of(new UserRequest(NAME, LOGIN, " "), PASSWORD),
                Arguments.of(new UserRequest(NAME, LOGIN, "\t"), PASSWORD),
                Arguments.of(new UserRequest(NAME, LOGIN, null), PASSWORD)
        );
    }

}
