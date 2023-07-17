package com.project.timeRegistry.model.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DAILY_REPORTS")
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day_report;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DAILY_REPORT_ID")
    private List<WorkedTime> workedTimes;

    public DailyReport(Long id) {
        this.id = id;
        this.day_report = LocalDate.now();
    }

    public Duration getDailyWorkedHours() {
        Duration duration = Duration.ZERO;
        for (WorkedTime w : this.workedTimes ) {
            duration.plus(w.getDuration());
        }
        return duration;
    }
}
