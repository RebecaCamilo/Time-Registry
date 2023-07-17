package com.project.timeRegistry.model.factory;

import com.project.timeRegistry.model.domain.DailyReport;

import java.time.LocalDate;
import java.util.List;

import static com.project.timeRegistry.model.factory.WorkedTimeFactoryTest.createValidWorkedTime;

public class DailyReportFactoryTest {

    public static DailyReport createValidDailyReport() {
        return DailyReport.builder()
                .id(1L)
                .day_report(LocalDate.now())
                .workedTimes(List.of(createValidWorkedTime()))
                .build();
    }
}
