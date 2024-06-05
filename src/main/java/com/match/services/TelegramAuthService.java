package com.match.services;

import com.match.domain.TelegramAuthData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelegramAuthService {

    // значение botToken будет установлено из конфигурационного файла или переменной окружения
    private final String botToken;

    public TelegramAuthService(@Value("${telegram.bot-token}") String botToken) {
        this.botToken = botToken;
    }

    public boolean validateHash(TelegramAuthData telegramAuthData) {
        // Вызов метода checkAuth для проверки хеша
        return telegramAuthData.checkAuth(botToken);
    }
}
