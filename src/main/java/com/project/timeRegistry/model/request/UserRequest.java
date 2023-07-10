package com.project.timeRegistry.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
