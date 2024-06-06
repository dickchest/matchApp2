package com.match.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserProfileDto {
    private String id;
    private String userId;
    private String firstName;
    private String secondName;
    private String userName;
    private String dateOfBirth; // Для DTO используем строковое представление даты
    private Integer gender;
    private String city;
    private LanguagesDto languages; // Отдельный DTO для languages, если это не Enum
    private Map<Integer, Integer> goals;
    private Boolean goalsShow;
    private List<Integer> genderLook;
    private Boolean genderLookShow;
    private List<String> values;
    private UserProfileQuestionsDto questions; // Отдельный DTO для questions
    private Map<String, Integer> toddlers;
    private List<String> interests;
    private List<String> photos;
    private String about;
}