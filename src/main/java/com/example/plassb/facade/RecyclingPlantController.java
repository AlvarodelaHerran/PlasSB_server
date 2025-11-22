package com.example.plassb.facade;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.plassb.dto.AssignRequestDto;
import com.example.plassb.dto.AssignResponseDto;
import com.example.plassb.dto.RecyclingPlantDto;
import com.example.plassb.entity.AssignmentRecord;
import com.example.plassb.entity.RecyclingPlant;
import com.example.plassb.service.RecyclingPlantService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/recyclingPlant")
public class RecyclingPlantController {

    private final RecyclingPlantService recyclingPlantService;

    public RecyclingPlantController(RecyclingPlantService recyclingPlantService) {
        this.recyclingPlantService = recyclingPlantService;
    }

    @GetMapping
    public RecyclingPlantDto getPlant() {
    	RecyclingPlant res = recyclingPlantService.getPlant();
        return RecyclingPlantDto.map(res);
    }
    
    @GetMapping("/capacity/{date}")
    public ResponseEntity<Integer> getPlantCapacity(@Parameter(
            description = "Date pour laquelle récupérer les données d'utilisation",
            example = "2025-11-22"
        )
        @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
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
