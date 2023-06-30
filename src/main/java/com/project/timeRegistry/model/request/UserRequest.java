package com.project.timeRegistry.model.request;

import com.project.timeRegistry.model.domain.MonthlyReport;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class UserRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

}
