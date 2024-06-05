package com.match.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramAuthData {
    private User user;
    private Long authDate;
    private String hash;
    private RegistrationData registrationData;
}
