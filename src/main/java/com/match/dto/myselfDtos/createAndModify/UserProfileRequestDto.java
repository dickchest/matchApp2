package com.match.dto.myselfDtos.createAndModify;

import com.match.dto.myselfDtos.UserProfileQuestionsDto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserProfileRequestDto {

    private String name;
    private String dateOfBirth; // Для DTO используем строковое представление даты
    private Integer gender;
    private String city;
    private List<String> languages; // list names
    private List<Integer> goals;
    private Boolean goalsShow;
    private List<Integer> genderLook;
    private Boolean genderLookShow;
    private List<String> values;
    private UserProfileQuestionsDto questions; // Отдельный DTO для questions
    private Map<String, Integer> toddlers;
    private List<String> interests;
}