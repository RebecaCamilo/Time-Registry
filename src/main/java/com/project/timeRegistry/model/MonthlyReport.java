package com.project.timeRegistry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MONTHLY_REPORTS")
public class MonthlyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Month month;

    @NotNull
    private Year year;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MONTHLY_REPORT_ID")
    private List<DailyReport> dailyReports;

    public MonthlyReport(Long id) {
        this.id = id;
        LocalDate reportDate = LocalDate.now();
        this.month = reportDate.getMonth();
        this.year = Year.of(reportDate.getYear());
    }

    public Duration getMonthlyWorkedHours(List<DailyReport> dailyReports) {
        Duration duration = Duration.ZERO;
        for (DailyReport d : dailyReports ) {
            duration.plus(d.getDailyWorkedHours());
        }
        return duration;
    }
}
