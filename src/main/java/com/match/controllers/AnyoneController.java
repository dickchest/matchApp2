package com.match.controllers;

import com.match.domain.dto.anyoneDtos.analyzeDto.AnalyzeRequestDto;
import com.match.domain.dto.anyoneDtos.analyzeDto.AnalyzeResponseDto;
import com.match.domain.dto.anyoneDtos.getAnyoneDto.GetAnyoneResponseDto;
import com.match.services.AnyoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/anyone")
@AllArgsConstructor
public class AnyoneController {
    private AnyoneService service;

    @PostMapping("/analyze")
    public ResponseEntity<AnalyzeResponseDto> analyze(
            @RequestParam("foto") MultipartFile photo,
            @RequestParam("name") String name,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") Integer gender,
            @RequestParam("relationshipType") String relationshipType,
            Principal principal) throws IOException, IllegalAccessException {

        if (photo == null || photo.isEmpty()) {
            System.out.println("файл пустой");
            // todo нужно выкинуть ошибку
        }

        AnalyzeRequestDto dto = AnalyzeRequestDto.builder()
                .photo(photo)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .relationshipType(relationshipType)
                .userId(principal.getName())
                .build();

        return new ResponseEntity<>(service.analyze(dto, principal), HttpStatus.CREATED);
    }

    @GetMapping("/{userUid}")
    public ResponseEntity<GetAnyoneResponseDto> getPeople(@PathVariable("userUid") String userUid, Principal principal) {
        return new ResponseEntity<>(service.getPeople(userUid, principal), HttpStatus.OK);
    }

}
