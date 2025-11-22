package com.example.plassb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import com.example.plassb.entity.AssignmentRecord;
import com.example.plassb.entity.AssignmentRecordId;

public interface AssignmentRecordRepository extends JpaRepository<AssignmentRecord, AssignmentRecordId> {
    List<AssignmentRecord> findByPlantId(Long plantId);
    List<AssignmentRecord> findByDate(LocalDate date);
}
