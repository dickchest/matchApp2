package com.match.domain.dto.authDtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FirebaseTokenAndStatusDto {

    private String access;
    private String refresh;
    private String status;

    public FirebaseTokenAndStatusDto(String access, String refresh, String status) {
        this.access = access;
        this.refresh = refresh;
        this.status = status;
    }
}
