package com.example.plassb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import com.example.plassb.entity.AssignmentRecord;

public interface AssignmentRecordRepository extends JpaRepository<AssignmentRecord, Long> {
    List<AssignmentRecord> findAll();
    List<AssignmentRecord> findByDate(LocalDate date);
}
