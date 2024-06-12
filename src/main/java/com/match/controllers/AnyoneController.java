package com.match.controllers;

import com.match.domain.dto.UserStatusDto;
import com.match.services.AnyoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;

@RestController
@RequestMapping("/anyone")
@AllArgsConstructor
public class AnyoneController {
    private AnyoneService service;

    @PostMapping("/analyze")
    public ResponseEntity<UserStatusDto> analyze(
            @RequestParam("foto") MultipartFile photo,
//            @RequestParam(value = "foto", required = false) MultipartFile photo,
            @RequestParam("name") String name,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") Integer gender,
            @RequestParam("relationshipType") String relationshipType,
            Principal principal) throws IOException, IllegalAccessException {

        if (photo.isEmpty()) {
            System.out.println("файл пустой");
            // создайем заглушку для файла
            photo = null;
        }

        return new ResponseEntity<>(service.analyze(photo, name, dateOfBirth, gender, relationshipType, principal), HttpStatus.CREATED);
    }
}
