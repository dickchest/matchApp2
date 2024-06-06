package com.match.domain;

import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserProfile {
    private String id;
    private String userId;
    private String firstName;
    private String secondName;
    private String userName;
    private Timestamp dateOfBirth;
    private Integer gender;
    private String city;
    private Languages languages;
    private Map<Integer, Integer> goals;
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
