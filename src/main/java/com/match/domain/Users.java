package com.match.domain;

import com.match.domain.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private UserStatus status;
//    private String firstName;
//    private String secondName;
}
