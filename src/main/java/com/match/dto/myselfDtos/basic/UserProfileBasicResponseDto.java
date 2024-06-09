package com.match.dto.myselfDtos.basic;

import lombok.Data;

@Data
public class UserProfileBasicResponseDto {

    private String name;
    private Integer age; // нужно подсчитать от даты рождения
    private Integer gender;
    private Integer profileComplied; // нужно уточнить, что это
}
