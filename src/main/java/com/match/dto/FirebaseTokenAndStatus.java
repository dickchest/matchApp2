package com.match.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FirebaseTokenAndStatus {

    private String firebaseToken;
    private String userStatus;

    public FirebaseTokenAndStatus(String firebaseToken, String userStatus) {
        this.firebaseToken = firebaseToken;
        this.userStatus = userStatus;
    }

}
