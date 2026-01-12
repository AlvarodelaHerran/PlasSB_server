package com.example.plassb.service;

import com.example.plassb.entity.RecyclingPlant;
import com.example.plassb.entity.AssignmentRecord;
import com.example.plassb.dao.RecyclingPlantRepository;
import com.example.plassb.dao.AssignmentRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecyclingPlantService {

    private final RecyclingPlantRepository plantRepository;
    private final AssignmentRecordRepository assignmentRepository;

    public RecyclingPlantService(RecyclingPlantRepository plantRepository,
                                 AssignmentRecordRepository assignmentRepository) {
        this.plantRepository = plantRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public RecyclingPlant getPlant() {
        RecyclingPlant plant = plantRepository.findById("PlasSB")
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        List<AssignmentRecord> filteredAssignments = plant.getAssignments()
                .stream()
                .filter(a -> a.getDate().equals(LocalDate.now()))
                .collect(Collectors.toList());
        
        plant.getAssignments().clear();
        plant.getAssignments().addAll(filteredAssignments);
        plant.setCurrentFill(filteredAssignments
        		.stream()
        		.mapToInt(AssignmentRecord::getFilling)
                .sum());

        return plant;
    }

    public AssignmentRecord assignDumpsterToPlant(int filling, int totalDumpster) {
        RecyclingPlant plant = plantRepository.findById("PlasSB")
                .orElseThrow(() -> new IllegalArgumentException("Plant not found"));

        AssignmentRecord record = new AssignmentRecord(plant, LocalDate.now(), filling, totalDumpster);
        plant.addAssignment(record);

        plantRepository.save(plant);
        return assignmentRepository.save(record);
    }

    public Integer getRemainingCapacity(LocalDate date) {

        int usedCapacity = assignmentRepository.findByDate(date)
                .stream()
                .mapToInt(AssignmentRecord::getFilling)
                .sum();

        return plantRepository.findById("PlasSB").orElse(null).getMaxCapacity() - usedCapacity;
    }
}
