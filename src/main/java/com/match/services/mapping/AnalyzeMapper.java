package com.match.services.mapping;

import com.match.domain.dto.anyone.AnalyzeRequestDto;
import com.match.domain.entity.AddedPeople;
import com.match.services.utils.TimeUtils;
import com.match.services.utils.file.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AnalyzeMapper {
    private final FileUtils fileUtils;

    public AddedPeople fromDto(AnalyzeRequestDto dto) throws IOException, IllegalAccessException {
        return AddedPeople.builder()
                .userId(dto.getUserId())
                .photo(fileUtils.uploadFileAndGetDownloadUrl(dto.getPhoto()))
                .name(dto.getName())
                .dateOfBirth(TimeUtils.convertToTimestamp(dto.getDateOfBirth()))
                .gender(dto.getGender())
                .relationshipType(dto.getRelationshipType())
                .build();
    }
}
