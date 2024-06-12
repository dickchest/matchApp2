package com.match.domain.dto.anyoneDtos.getAnyoneDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAnyoneLanguagesResponseDto {
    private String name;
    private String logo;
}
