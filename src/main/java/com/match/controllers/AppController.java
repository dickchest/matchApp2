package com.match.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.match.domain.TelegramAuthData;
import com.match.dto.FirebaseTokenAndStatus;
import com.match.services.FirebaseAuthenticationService;
import com.match.validation.InvalidTelegramHashException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myself")
public class AppController {

    private final FirebaseAuthenticationService firebaseAuthenticationService;

    public AppController(FirebaseAuthenticationService firebaseAuthenticationService) {
        this.firebaseAuthenticationService = firebaseAuthenticationService;
    }

    @PostMapping("/enter")
    public ResponseEntity<?> enter(@RequestBody TelegramAuthData telegramAuthData) {
        try {
            // Аутентификация пользователя и получение Firebase Token
            String firebaseToken = firebaseAuthenticationService.authenticateWithTelegramData(telegramAuthData);
            return ResponseEntity.ok(new FirebaseTokenAndStatus(firebaseToken, "USER_STATUS"));
        } catch (InvalidTelegramHashException | FirebaseAuthException e) {
            // Если хеш не совпадает, возвращаем ошибку 511
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Invalid hash");
        }
    }
}
