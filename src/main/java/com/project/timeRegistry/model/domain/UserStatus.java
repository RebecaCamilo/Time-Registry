package com.project.timeRegistry.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    INACTIVE(0, "INACTIVE"),
    ACTIVE(1, "ACTIVE");

    private Integer id;
    private String description;

}
