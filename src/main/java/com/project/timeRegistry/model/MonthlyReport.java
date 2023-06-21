package com.project.timeRegistry.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "MONTHLY_REPORTS")
public class MonthlyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final Month month;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MONTHLY_REPORT_ID")
    private List<WorkedTime> workedTimes;

    public MonthlyReport(Long id) {
        this.id = id;
        LocalDate reportDate = LocalDate.now();
        this.month = reportDate.getMonth();
    }
}
