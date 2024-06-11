package com.match.services;

import com.match.domain.dto.UserStatusDto;
import com.match.domain.dto.anyone.AnalyzeRequestDto;
import com.match.domain.entity.AddedPeople;
import com.match.repository.AddedPeopleRepository;
import com.match.services.mapping.AnalyzeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Service
@AllArgsConstructor
public class AnyoneService {
    private final AddedPeopleRepository addedPeopleRepository;
    private final AnalyzeMapper analyzeMapper;

    public UserStatusDto analyze(MultipartFile photo, String name, String dateOfBirth, Integer gender, String relationshipType, Principal principal) throws IOException, IllegalAccessException {
        AnalyzeRequestDto dto = AnalyzeRequestDto.builder()
                .photo(photo)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .relationshipType(relationshipType)
                .userId(principal.getName())
                .build();

        AddedPeople addedPeople = analyzeMapper.fromDto(dto);

        return UserStatusDto.builder()
                .status(addedPeopleRepository.save(addedPeople))
                .build();
    }
}
