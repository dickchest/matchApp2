package com.match.services.utils.auth;

import com.match.domain.dto.authDtos.TelegramAuthDataDto;
import com.match.validation.TelegramHashValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelegramAuthService {

    private final String botToken;

    // значение botToken будет установлено из конфигурационного файла или переменной окружения
    public TelegramAuthService(@Value("${telegram.bot-token}") String botToken) {
        this.botToken = botToken;
    }

    public boolean validateHash(TelegramAuthDataDto telegramAuthDataDto) {
        // Вызов метода checkAuth для проверки хеша
        return TelegramHashValidation.checkAuth(botToken, telegramAuthDataDto);
    }
}
