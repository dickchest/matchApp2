package com.match.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TelegramAuthData {
    private User user;
    private Long authDate;
    private String hash;
    private RegistrationData registrationData;

    // Класс для представления данных пользователя Telegram
    @Getter
    @Setter
    public static class User {
        private Integer id;
        @JsonProperty("is_bot")
        private Boolean isBot;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String username;
        @JsonProperty("language_code")
        private String languageCode;
    }

    // Класс для представления регистрационных данных
    @Getter
    @Setter
    public static class RegistrationData {
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        @JsonProperty("birth_date")
        private String birthDate;
    }

    // Метод для проверки хэша
    public boolean checkAuth(String botToken) {
        try {
            // Создание строки данных в соответствии с правилами Telegram
            Map<String, String> fields = new HashMap<>();
            fields.put("id", String.valueOf(user.getId()));
            fields.put("first_name", user.getFirstName());
            if (user.getLastName() != null) fields.put("last_name", user.getLastName());
            if (user.getUsername() != null) fields.put("username", user.getUsername());
            fields.put("auth_date", String.valueOf(authDate));
            String dataCheckString = fields.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .reduce((a, b) -> a + "\n" + b)
                    .orElse("");

            // Хеширование botToken + dataCheckString
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update((botToken + dataCheckString).getBytes(StandardCharsets.UTF_8));
            String computedHash = bytesToHex(messageDigest.digest());

            System.out.println("computedHash: " + computedHash);
            System.out.println("hash: "  + hash);

            // Сравнение вычисленного хэша с предоставленным хэшем
            return computedHash.equals(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    // Вспомогательный метод для преобразования байтов в строку в шестнадцатеричном формате
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
