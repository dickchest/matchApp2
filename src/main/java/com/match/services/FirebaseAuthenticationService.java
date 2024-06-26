package com.match.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.match.domain.entity.enums.UserStatus;
import com.match.domain.dto.authDtos.FirebaseTokenAndStatusDto;
import com.match.domain.dto.authDtos.TelegramAuthDataDto;
import com.match.repository.UserStatusRepository;
import com.match.services.utils.auth.TelegramAuthService;
import com.match.services.utils.auth.TokenGeneration;
import com.match.validation.InvalidTelegramHashException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseAuthenticationService {

    private final TelegramAuthService telegramAuthService;
    private final TokenGeneration tokenGeneration;
    private final UserStatusRepository userStatusRepository;

    public FirebaseAuthenticationService(TelegramAuthService telegramAuthService, TokenGeneration tokenGeneration, UserStatusRepository userStatusRepository) {
        this.telegramAuthService = telegramAuthService;
        this.tokenGeneration = tokenGeneration;
        this.userStatusRepository = userStatusRepository;
    }

    public FirebaseTokenAndStatusDto authenticateWithTelegramData(TelegramAuthDataDto telegramAuthDataDto) throws ExecutionException, InterruptedException {
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
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setUid(uid)
                    .setEmail(telegramAuthDataDto.getUser().getUsername() + "@telegram.com")
                    .setDisplayName(telegramAuthDataDto.getUser().getUsername());

            try {
                userRecord = FirebaseAuth.getInstance().createUser(request);
                // установка статуса в firestone
                userStatusRepository.save(uid, UserStatus.REGISTER);
            } catch (FirebaseAuthException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Генерация Firebase Token
        Map<String, String> tokens = tokenGeneration.generate(userRecord);
        // добыть статус у пользователя
        UserStatus userStatus = userStatusRepository.getStatus(uid);

        return new FirebaseTokenAndStatusDto(
                tokens.get("access"), tokens.get("refresh"), userStatus.toString());
    }
}
