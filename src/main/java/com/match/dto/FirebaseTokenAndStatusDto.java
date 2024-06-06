package com.match.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FirebaseTokenAndStatusDto {

    private String firebaseToken;
    private String userStatus;

    public FirebaseTokenAndStatusDto(String firebaseToken, String userStatus) {
        this.firebaseToken = firebaseToken;
        this.userStatus = userStatus;
    }

}
