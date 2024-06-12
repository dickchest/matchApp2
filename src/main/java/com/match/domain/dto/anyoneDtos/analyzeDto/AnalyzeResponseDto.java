package com.match.domain.dto.anyoneDtos.analyzeDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnalyzeResponseDto {
    @JsonProperty("user_id")
    String userId;
}
