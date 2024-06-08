package com.match.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserProfile {
    private String userId;
    private String firstName;
    private String secondName;
    private String name;
    private LocalDate dateOfBirth;
    private Integer gender;
    private String city;
    private List<Languages> languages;
    private List<Integer> goals;
    private Boolean goalsShow;
    private List<Integer> genderLook;
    private Boolean genderLookShow;
    private List<String> values;
    private UserProfileQuestions questions;
    private Map<String, Integer> toddlers;
    private List<String> interests;
    private List<String> photos;
    private String about;
}
