package com.match.controllers;

import com.match.domain.dto.UserStatusDto;
import com.match.domain.dto.myselfDtos.basic.UserProfileBasicResponseDto;
import com.match.domain.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import com.match.services.MyselfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/myself")
@AllArgsConstructor
public class MyselfController {
    private MyselfService service;

    @GetMapping("/basic")
    public ResponseEntity<UserProfileBasicResponseDto> getBasic(Principal principal) {
        return new ResponseEntity<>(service.getBasic(principal), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserStatusDto> create(@RequestBody UserProfileRequestDto dto, Principal principal) {
        return new ResponseEntity<>(service.create(dto, principal), HttpStatus.CREATED);
    }

    @PatchMapping("/modify")
    public ResponseEntity<String> modify(@RequestBody UserProfileRequestDto dto, Principal principal) {
        service.modify(dto, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/analyze")
    public ResponseEntity<UserStatusDto> analyze(@RequestParam("foto") MultipartFile photo, Principal principal) {
        return new ResponseEntity<>(service.analyze(photo, principal), HttpStatus.OK);
    }
}
