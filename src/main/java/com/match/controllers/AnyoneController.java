package com.match.controllers;

import com.match.dto.UserStatusDto;
import com.match.services.AnyoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/anyone")
@AllArgsConstructor
public class AnyoneController {
    private AnyoneService service;

    @PostMapping("/analize")
    public ResponseEntity<UserStatusDto> analyze(
            @RequestParam("foto") MultipartFile photo,
            @RequestParam("name") String name,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") Integer gender,
            @RequestParam("relationshipType") String relationshipType,
            Principal principal) {

        return new ResponseEntity<>(service.analyze(photo, principal), HttpStatus.OK);
    }
}
