package com.match.services.mapping;

import com.match.domain.UserProfile;
import com.match.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserProfileMapper {

//    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    UserProfileDto toUserProfileDto(UserProfile userProfile);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    UserProfile toUserProfile(UserProfileDto userProfileDto);
}
