package com.project.timeRegistry.model.factory;

import com.project.timeRegistry.model.domain.WorkedTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkedTimeFactoryTest {

    public static WorkedTime createValidWorkedTime() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish =LocalDateTime.now().plus(Duration.ofMinutes(20L));
        return WorkedTime.builder()
                .id(1L)
                .start(start)
                .finish(finish)
                .duration(Duration.between(start, finish))
                .activityDescription("something")
                .build();
    }
}
