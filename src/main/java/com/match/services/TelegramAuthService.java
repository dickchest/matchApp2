package com.match.services;

import com.match.domain.TelegramAuthData;
import org.springframework.stereotype.Service;

@Service
public class TelegramAuthService {

    private final String botToken;

    public TelegramAuthService(String botToken) {
        this.botToken = botToken;
    }

    public boolean validateHash(TelegramAuthData telegramAuthData) {
        // Вызов метода checkAuth для проверки хеша
        return telegramAuthData.checkAuth(botToken);
    }

}
