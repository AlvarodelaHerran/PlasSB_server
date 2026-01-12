package com.example.plassb.facade;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.plassb.dto.AssignRequestDto;
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
    
    @GetMapping("/capacity")
    public ResponseEntity<Integer> getPlantCapacity(@Parameter(
            description = "Date pour laquelle récupérer les données d'utilisation",
            example = "2025-11-22")
	        @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Integer remaining = recyclingPlantService.getRemainingCapacity(date);
        return remaining != null ? ResponseEntity.ok(remaining) : ResponseEntity.notFound().build();
    }

    @PostMapping("/assignDumpster")
    public ResponseEntity<?> assignDumpster(@RequestBody AssignRequestDto request) {
        try {
            recyclingPlantService.assignDumpsterToPlant(
                    request.getFilling(),
                    request.getTotalDumpsters()
            );
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur serveur : " + e.getMessage());
        }
    }
}
