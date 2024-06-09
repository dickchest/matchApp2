package com.match.domain;

import com.google.cloud.Timestamp;
import lombok.*;

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
    private String name;
    private Timestamp dateOfBirth;
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
