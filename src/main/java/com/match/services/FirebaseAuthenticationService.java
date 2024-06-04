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

    public String authenticateWithTelegramData(TelegramAuthData telegramAuthData) throws FirebaseAuthException {
        // проверка хеша
        if (!telegramAuthService.validateHash(telegramAuthData)) {
            throw new InvalidTelegramHashException("Invalid hash");
        }

        // проверка пользователя в фаербейз и создание/обновление записи
        String uid = telegramAuthData.getUser().getId().toString();
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
        if (userRecord == null) {
            // создание нового пользователя
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setUid(uid)
                    .setEmail(telegramAuthData.getUser().getUsername() + "@telegram.com")
            // другие поля пользователя...
            ;
            userRecord = FirebaseAuth.getInstance().createUser(request);
        } else {
            // обновление существующего пользователя
            // ..
        }

        // Генерация Firebase Token
        String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
        return customToken;
    }
}
