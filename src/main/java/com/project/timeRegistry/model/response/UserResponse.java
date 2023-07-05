package com.project.timeRegistry.model.response;

import com.project.timeRegistry.model.domain.UserStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String login;

    private String password;

    private UserStatus status;

}
