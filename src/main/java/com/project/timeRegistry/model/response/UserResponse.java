package com.project.timeRegistry.model.response;

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

    private String status;

}
