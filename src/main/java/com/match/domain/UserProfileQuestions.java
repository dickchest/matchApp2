package com.match.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfileQuestions {
    private String preferToLive;
    private String idealVacation;
    private List<String> howDoYouLove;
    private String preferToEat;
    private String preferToVisit;
    private String afterWork;
    private String whatIsBest;
    private String generallyPrefer;
    private String spendTheDay;
}
