package com.match.domain.dto.anyoneDtos.getAnyoneDto;

import com.match.domain.dto.myselfDtos.UserProfileQuestionsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class GetAnyoneResponseDto {
    private List<String> fotos;
    private String name;
    private Integer age;
    private Integer gender;
    private String city;
    private List<GetAnyoneLanguagesResponseDto> languages; // list names
    private String zodiac;
    private List<Integer> goals;
    private List<String> values;
    private String bio;
    private UserProfileQuestionsDto questions; // Отдельный DTO для questions
    private Map<String, Integer> toddlers;
}
