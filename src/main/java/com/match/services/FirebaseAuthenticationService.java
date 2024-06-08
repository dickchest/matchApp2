package com.match.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.match.dto.authDtos.FirebaseTokenAndStatusDto;
import com.match.dto.authDtos.TelegramAuthDataDto;
import com.match.validation.InvalidTelegramHashException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FirebaseAuthenticationService {

    private final TelegramAuthService telegramAuthService;
    private final TokenGeneration tokenGeneration;

    public FirebaseAuthenticationService(TelegramAuthService telegramAuthService, TokenGeneration tokenGeneration) {
        this.telegramAuthService = telegramAuthService;
        this.tokenGeneration = tokenGeneration;
    }

    public FirebaseTokenAndStatusDto authenticateWithTelegramData(TelegramAuthDataDto telegramAuthDataDto) {
        // проверка хеша
        if (!telegramAuthService.validateHash(telegramAuthDataDto)) {
            throw new InvalidTelegramHashException("Invalid hash");
        }

        // проверка пользователя в фаербейз и создание/обновление записи
        String uid = telegramAuthDataDto.getUser().getId().toString();
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(uid);
            // если нет такого юзера, перебросит в блок обработки исключения

            // обновление существующего пользователя
            // ..
        } catch (FirebaseAuthException e) {
            // Если юзера с таким айди нет,
            // создание нового пользователя
            UserRecord.CreateRequest request = new UserRecord.CreateRequest().setUid(uid).setEmail(telegramAuthDataDto.getUser().getUsername() + "@telegram.com").setDisplayName(telegramAuthDataDto.getUser().getUsername());
            // todo добавить установку статуса

            try {
                userRecord = FirebaseAuth.getInstance().createUser(request);
            } catch (FirebaseAuthException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Генерация Firebase Token
        Map<String, String> tokens = tokenGeneration.generate(userRecord);

        return new FirebaseTokenAndStatusDto(
                tokens.get("access"), tokens.get("refresh"), "USER_STATUS");

    }
}
