package com.example.plassb;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.plassb.dao.RecyclingPlantRepository;
import com.example.plassb.dao.AssignmentRecordRepository;
import com.example.plassb.entity.RecyclingPlant;
import com.example.plassb.entity.AssignmentRecord;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RecyclingPlantRepository recyclingPlantRepository;
    private final AssignmentRecordRepository assignmentRecordRepository;

    public DatabaseInitializer(RecyclingPlantRepository recyclingPlantRepository,
                               AssignmentRecordRepository assignmentRecordRepository) {
        this.recyclingPlantRepository = recyclingPlantRepository;
        this.assignmentRecordRepository = assignmentRecordRepository;
    }

    @Override
    public void run(String... args) {
        RecyclingPlant r1 = new RecyclingPlant("PlasSB", "Bilbao", 48010, 5000);
        recyclingPlantRepository.save(r1);

        AssignmentRecord a1 = new AssignmentRecord(r1, LocalDate.now().minusDays(3), 900, 2);
        AssignmentRecord a2 = new AssignmentRecord(r1, LocalDate.now(), 1200, 1);

        r1.addAssignment(a1);
        r1.addAssignment(a2);

        assignmentRecordRepository.saveAll(Arrays.asList(a1, a2));

        recyclingPlantRepository.save(r1);
    }
}
