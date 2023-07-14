package com.project.timeRegistry.model.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @Parameter(name = "User name", description = "Name of user", example = "Rebeca")
    @NotBlank
    private String name;

    @Parameter(name = "User login", description = "Login of user", example = "rebeca.camilo")
    @NotBlank
    private String login;

    @Parameter(name = "User password", description = "Password of user", example = "4nyStr0ngP4ss")
    @NotBlank
    private String password;

}
