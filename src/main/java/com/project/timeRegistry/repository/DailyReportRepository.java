package com.project.timeRegistry.repository;

import com.project.timeRegistry.model.domain.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {
}
