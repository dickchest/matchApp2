package com.match.services.mapping;

import com.google.cloud.Timestamp;
import com.match.domain.UserProfile;
import com.match.dto.myselfDtos.basic.UserProfileBasicResponseDto;
import com.match.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import org.mapstruct.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

//    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "languagesNames", source = "languages", qualifiedByName = "languagesToNames")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "timestampToString")
    UserProfileRequestDto toDto(UserProfile userProfile);

    @Mapping(target = "age", source = "dateOfBirth", qualifiedByName = "calculateAgeFromTimestamp")
    UserProfileBasicResponseDto toBasicResponseDto(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "languages", source = "languagesNames", qualifiedByName = "namesToLanguages")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToTimestamp")
    UserProfile fromDto(UserProfileRequestDto userProfileRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "languages", source = "languages", qualifiedByName = "namesToLanguages")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToTimestamp")
    void modifyModelFromDto(UserProfileRequestDto dto, @MappingTarget UserProfile userProfile);

    @Named("timestampToString")
    default String convertTimestampToString(Timestamp timestamp) {
        return timestamp != null ? dateFormat.format(timestamp) : null;
    }

    @Named("stringToTimestamp")
    default Timestamp convertStringToTimestamp(String date) {
        if (date == null) {
            return null;
        }
        try {
            Date parseDate = dateFormat.parse(date);
            return Timestamp.of(parseDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Named("calculateAgeFromTimestamp")
    default Integer calculateAge(Timestamp dateOfBirth) {
        if (dateOfBirth == null) {
            return null;
        }

        Date now = new Date();
        long diffInMillis = Math.abs(now.getTime() - dateOfBirth.toDate().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        return (int) (diff / 365);
    }
}
