package com.match.domain.dto.anyoneDtos.analyzeDto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AnalyzeRequestDto {
    private String userId; // какой user вводил запрос на анализ этого человека
    private MultipartFile photo;
    private String name;
    private String  dateOfBirth;
    private Integer gender;
    private String relationshipType;
}
