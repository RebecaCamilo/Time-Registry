package com.project.timeRegistry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DAILY_REPORTS")
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer day;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MONTHLY_REPORT_ID")
    private List<WorkedTime> workedTimes;

    public DailyReport(Long id) {
        this.id = id;
        LocalDate reportDate = LocalDate.now();
        this.day = reportDate.getDayOfMonth();
    }

    public Duration getDailyWorkedHours() {
        Duration duration = Duration.ZERO;
        for (WorkedTime w : this.workedTimes ) {
            duration.plus(w.getDuration());
        }
        return duration;
    }
}
