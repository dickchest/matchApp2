package com.match.controllers;

import com.match.domain.dto.authDtos.FirebaseTokenAndStatusDto;
import com.match.domain.dto.authDtos.TelegramAuthDataDto;
import com.match.services.FirebaseAuthenticationService;
import com.match.validation.InvalidTelegramHashException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class AppController {

    private final FirebaseAuthenticationService firebaseAuthenticationService;

    public AppController(FirebaseAuthenticationService firebaseAuthenticationService) {
        this.firebaseAuthenticationService = firebaseAuthenticationService;
    }

    @PostMapping("/enter")
    public ResponseEntity<?> enter(@RequestBody TelegramAuthDataDto telegramAuthDataDto) {
        try {
            // Аутентификация пользователя и получение Firebase Token
            FirebaseTokenAndStatusDto response = firebaseAuthenticationService.authenticateWithTelegramData(telegramAuthDataDto);
            return ResponseEntity.ok(response);
        } catch (InvalidTelegramHashException e) {
            // Если хеш не совпадает, возвращаем ошибку 511
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Invalid hash");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
