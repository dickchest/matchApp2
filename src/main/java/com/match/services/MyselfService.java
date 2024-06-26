package com.match.services;

import com.match.domain.entity.UserProfile;
import com.match.domain.entity.enums.UserStatus;
import com.match.domain.dto.UserStatusDto;
import com.match.domain.dto.myselfDtos.basic.UserProfileBasicResponseDto;
import com.match.domain.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import com.match.repository.AuthRepository;
import com.match.repository.UserProfileRepository;
import com.match.repository.UserStatusRepository;
import com.match.services.mapping.UserProfileMapper;
import com.match.services.mapping.UserStatusMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        UserProfile userProfile = repository.findById(userUid)
                .orElseThrow(() -> new RuntimeException("Not Found!"));

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
        UserProfile userProfile = repository.findById(userUid)
                .orElseThrow(() -> new RuntimeException("Not Found!"));

        // Обновление существующего userProfile данными из dto
        // Важно: этот вызов теперь обновляет userProfile, а не создаёт новый объект
        mapper.modifyModelFromDto(dto, userProfile);

        // сохраняем в репозитории
        repository.save(userProfile);
    }

    public UserStatusDto analyze(MultipartFile photo, Principal principal) {

        // getCurrentUser
        String userUid = authRepository.getUserUid(principal);

        // todo бд для анализа
        // Здесь логика анализа
        // также нужно продумать, куда сохранять все данные после анализа

        // устанавливаем статус
        userStatusRepository.save(userUid, UserStatus.ANALYSED);

        return userStatusMapper.toDto(userStatusRepository.getStatus(userUid));
    }
}
