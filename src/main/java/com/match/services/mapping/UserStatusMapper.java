package com.match.services.mapping;

import com.match.domain.entity.enums.UserStatus;
import com.match.domain.dto.UserStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserStatusMapper {

//    UserStatusMapper INSTANCE = Mappers.getMapper(UserStatusMapper.class);

    @Mapping(source = "userStatus", target = "status", qualifiedByName = "enumNameToString")
    UserStatusDto toDto(UserStatus userStatus);

    @Named("enumNameToString")
    default String toString(UserStatus userStatus) {
        return userStatus != null ? userStatus.toString() : null;
    }

    default UserStatus fromString(String status) {
        if (status == null) {
            return null;
        }

        try {
            return UserStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
