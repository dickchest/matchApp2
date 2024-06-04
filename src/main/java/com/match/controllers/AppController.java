package com.match.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myself")
@AllArgsConstructor
public class AppController {

    @GetMapping("/token")
    public String getToken() {
        System.out.println("token");
        return "Hello, World";
    }

}
