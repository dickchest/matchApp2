package com.match.dto.myselfDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProfileQuestionsDto {
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
