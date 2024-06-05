package com.match.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.match.domain.TelegramAuthData;
import com.match.validation.InvalidTelegramHashException;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthenticationService {

    private final TelegramAuthService telegramAuthService;

    public FirebaseAuthenticationService(TelegramAuthService telegramAuthService) {
        this.telegramAuthService = telegramAuthService;
    }

    public String authenticateWithTelegramData(TelegramAuthData telegramAuthData) {
        // проверка хеша
        if (!telegramAuthService.validateHash(telegramAuthData)) {
            throw new InvalidTelegramHashException("Invalid hash");
        }

        // проверка пользователя в фаербейз и создание/обновление записи
        String uid = telegramAuthData.getUser().getId().toString();
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(uid);
            // если нет такого юзера, перебросит в блок обработки исключения

            // обновление существующего пользователя
            // ..
        } catch (FirebaseAuthException e) {
            // Если юзера с таким айди нет,
            // создание нового пользователя
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setUid(uid)
                    .setEmail(telegramAuthData.getUser().getUsername() + "@telegram.com")
                    .setDisplayName(telegramAuthData.getUser().getUsername());
            // другие поля пользователя...
            // todo нужно продумать переход на базу данных с полными данными пользователя

            try {
                userRecord = FirebaseAuth.getInstance().createUser(request);
            } catch (FirebaseAuthException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Генерация Firebase Token
        String customToken = null;
        try {
            customToken = FirebaseAuth.getInstance().createCustomToken(userRecord.getUid());
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        return customToken;
    }
}
