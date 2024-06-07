package com.match.services.mapping;

import com.match.domain.UserProfile;
import com.match.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserProfileMapper {

//    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    UserProfileDto toDto(UserProfile userProfile);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    UserProfile fromDto(UserProfileDto userProfileDto);
}
