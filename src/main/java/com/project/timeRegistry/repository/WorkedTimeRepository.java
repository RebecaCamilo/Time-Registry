package com.project.timeRegistry.repository;

import com.project.timeRegistry.model.domain.WorkedTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkedTimeRepository extends JpaRepository<WorkedTime, Long> {
}
