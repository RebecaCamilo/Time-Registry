package com.project.timeRegistry.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WORKED_TIMES")
public class WorkedTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime finish;

    private Duration duration;

    private String activityDescription;

}
