package com.match.services;

import com.match.domain.dto.anyoneDtos.analyzeDto.AnalyzeRequestDto;
import com.match.domain.dto.anyoneDtos.analyzeDto.AnalyzeResponseDto;
import com.match.domain.dto.anyoneDtos.getAnyoneDto.GetAnyoneResponseDto;
import com.match.domain.entity.AddedPeople;
import com.match.domain.entity.UserProfile;
import com.match.repository.AddedPeopleRepository;
import com.match.repository.UserProfileRepository;
import com.match.services.mapping.AnalyzeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;

@Service
@AllArgsConstructor
public class AnyoneService {
    private final AddedPeopleRepository addedPeopleRepository;
    private final AnalyzeMapper analyzeMapper;
    private final UserProfileRepository userProfileRepository;

    public AnalyzeResponseDto analyze(AnalyzeRequestDto dto, Principal principal) throws IOException, IllegalAccessException {

        AddedPeople addedPeople = analyzeMapper.fromDto(dto);

        return AnalyzeResponseDto.builder()
                .userId(addedPeopleRepository.save(addedPeople))
                .build();
    }


    public GetAnyoneResponseDto getPeople(String userUid, Principal principal) {
        UserProfile userProfile = userProfileRepository.findById(userUid)
                .orElseThrow(() -> new RuntimeException("Not found!"));

        GetAnyoneResponseDto dto = GetAnyoneResponseDto.builder().build();

        return dto;
    }
}
