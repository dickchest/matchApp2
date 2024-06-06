package com.match.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramAuthDataDto {
    private UserDto user;
    private Long authDate;
    private String hash;
    private RegistrationDataDto registrationDataDto;
}
