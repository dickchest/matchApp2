package com.match.controllers;

import com.match.dto.UserStatusDto;
import com.match.dto.myselfDtos.basic.UserProfileBasicResponseDto;
import com.match.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import com.match.services.MyselfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("enter in create endpoint");
        return new ResponseEntity<>(service.create(dto, principal), HttpStatus.CREATED);
    }
}
