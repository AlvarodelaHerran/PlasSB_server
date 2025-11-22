package com.example.plassb.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.plassb.dto.AssignRequestDto;
import com.example.plassb.dto.AssignResponseDto;
import com.example.plassb.entity.AssignmentRecord;
import com.example.plassb.entity.RecyclingPlant;
import com.example.plassb.service.RecyclingPlantService;

@RestController
@RequestMapping("/recyclingPlants")
public class RecyclingPlantController {

    private final RecyclingPlantService recyclingPlantService;

    public RecyclingPlantController(RecyclingPlantService recyclingPlantService) {
        this.recyclingPlantService = recyclingPlantService;
    }

    @GetMapping
    public Optional<RecyclingPlant> getPlant() {
        return recyclingPlantService.getPlant();
    }
    
    @GetMapping("/{plantId}/capacity")
    public ResponseEntity<Integer> getPlantCapacity(@RequestParam LocalDate date) {
        Integer remaining = recyclingPlantService.getRemainingCapacity(date);
        return remaining != null ? ResponseEntity.ok(remaining) : ResponseEntity.notFound().build();
    }

    @PostMapping("/assignDumpster")
    public ResponseEntity<AssignResponseDto> assignDumpster(@RequestBody AssignRequestDto request) {
        AssignmentRecord result = recyclingPlantService.assignDumpsterToPlant(
                request.getDumpsterId(),
                request.getEmployeeId(),
                LocalDate.now(),
                request.getFilling()
        );
        return ResponseEntity.ok(AssignResponseDto.map(result.getPlant().getId(), 
        		result.getDumpsterId(), 
        		result.getEmployeeId(), 
        		result.getDate()));
    }
}
