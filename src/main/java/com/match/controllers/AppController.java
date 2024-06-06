package com.match.controllers;

import com.match.dto.TelegramAuthDataDto;
import com.match.dto.FirebaseTokenAndStatusDto;
import com.match.services.FirebaseAuthenticationService;
import com.match.validation.InvalidTelegramHashException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myself")
public class AppController {

    private final FirebaseAuthenticationService firebaseAuthenticationService;

    public AppController(FirebaseAuthenticationService firebaseAuthenticationService) {
        this.firebaseAuthenticationService = firebaseAuthenticationService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/enter")
    public ResponseEntity<?> enter(@RequestBody TelegramAuthDataDto telegramAuthDataDto) {
        try {
            System.out.println(telegramAuthDataDto.toString());
            // Аутентификация пользователя и получение Firebase Token
            String firebaseToken = firebaseAuthenticationService.authenticateWithTelegramData(telegramAuthDataDto);
            return ResponseEntity.ok(new FirebaseTokenAndStatusDto(firebaseToken, "USER_STATUS"));
        } catch (InvalidTelegramHashException e) {
            // Если хеш не совпадает, возвращаем ошибку 511
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Invalid hash");
        }
    }
}
