package com.project.timeRegistry.model.domain;

import lombok.*;

import javax.persistence.*;
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

    private LocalDateTime start;

    private LocalDateTime finish;

    private Duration duration;

    private String activityDescription;

    public WorkedTime(LocalDateTime start, LocalDateTime finish, String activityDescription) {
        this.start = start;
        this.finish = finish;
        this.duration = Duration.between(start, finish);
        this.activityDescription = activityDescription;
    }

    public Duration calcDuration(LocalDateTime start, LocalDateTime finish) {
        return this.duration = Duration.between(start, finish);
    }

}
