package com.match.services;

import com.match.domain.UserProfile;
import com.match.domain.enums.UserStatus;
import com.match.dto.UserStatusDto;
import com.match.dto.myselfDtos.basic.UserProfileBasicResponseDto;
import com.match.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import com.match.repository.AuthRepository;
import com.match.repository.UserProfileRepository;
import com.match.repository.UserStatusRepository;
import com.match.services.mapping.UserProfileMapper;
import com.match.services.mapping.UserStatusMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class MyselfService {
    private final AuthRepository authRepository;
    private final UserProfileRepository repository;
    private final UserStatusRepository userStatusRepository;
    private final UserProfileMapper mapper;
    private final UserStatusMapper userStatusMapper;

    public UserProfileBasicResponseDto getBasic(Principal principal) {

        // getCurrentUser
        String userUid = authRepository.getUserUid(principal);

        // UserProfileEntity
        UserProfile userProfile = repository.get(userUid);

        return mapper.toBasicResponseDto(userProfile);
    }


    public UserStatusDto create(UserProfileRequestDto dto, Principal principal) {

        // getCurrentUser
        String userUid = authRepository.getUserUid(principal);

        // UserProfileEntity
        UserProfile userProfile = mapper.fromDto(dto);
        userProfile.setUserId(userUid);

        // сохраняем в репозитории
        repository.save(userProfile);

        // устанавливаем статус
        userStatusRepository.save(userUid, UserStatus.ADDED);

        return userStatusMapper.toDto(userStatusRepository.getStatus(userUid));
    }

    public void modify(UserProfileRequestDto dto, Principal principal) {

        // getCurrentUser
        String userUid = authRepository.getUserUid(principal);

        // UserProfileEntity
        UserProfile userProfile = mapper.fromDto(dto);
        userProfile.setUserId(userUid);

        // сохраняем в репозитории
        repository.save(userProfile);
    }
}
