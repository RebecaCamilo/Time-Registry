package com.project.TimeRegistry.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class MonthlyReport {

    private final Long id;
    private final Month month;
    private List<WorkedTime> workedTimes;

    public MonthlyReport(Long id) {
        this.id = id;
        LocalDate reportDate = LocalDate.now();
        this.month = reportDate.getMonth();
    }
}
