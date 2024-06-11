package com.match.domain.dto.authDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramAuthDataDto {
    @JsonProperty("query_id")
    private String queryId;
    private UserDto user;

    @JsonProperty("auth_date")
    private Long authDate;
    private String hash;
//    private RegistrationDataDto registrationDataDto;
}
