package com.match.services.mapping;

import com.google.cloud.Timestamp;
import com.match.domain.entity.UserProfile;
import com.match.domain.dto.myselfDtos.basic.UserProfileBasicResponseDto;
import com.match.domain.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserProfileMapper {

    protected LanguagesMapper languagesMapper;

    @Autowired
    public void setLanguagesMapper(LanguagesMapper languagesMapper) {
        this.languagesMapper = languagesMapper;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "languages", source = "languages", qualifiedByName = "languagesToNames")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "timestampToString")
    abstract UserProfileRequestDto toDto(UserProfile userProfile);

    @Mapping(target = "age", source = "dateOfBirth", qualifiedByName = "calculateAgeFromTimestamp")
    @Mapping(target = "profileComplied", ignore = true)
    public abstract UserProfileBasicResponseDto toBasicResponseDto(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "languages", source = "languages", qualifiedByName = "namesToLanguages")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToTimestamp")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "about", ignore = true)
    public abstract UserProfile fromDto(UserProfileRequestDto userProfileRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "languages", source = "languages", qualifiedByName = "namesToLanguages")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToTimestamp")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "about", ignore = true)
    public abstract void modifyModelFromDto(UserProfileRequestDto dto, @MappingTarget UserProfile userProfile);

    @Named("timestampToString")
    protected String convertTimestampToString(Timestamp timestamp) {
        return timestamp != null ? dateFormat.format(timestamp) : null;
    }

    @Named("languagesToNames")
    protected List<String> languagesToNames(List<String> uids) {
        if (uids == null) return null;
        return uids.stream()
                .map(x -> languagesMapper.toLanguageFromUid(x).getName())
                .collect(Collectors.toList());
    }

    @Named("namesToLanguages")
    protected List<String> namesToLanguages(List<String> name) {
        if (name == null) return null;
        return name.stream()
                .map(x -> languagesMapper.toLanguageFromName(x).getUid())
                .collect(Collectors.toList());
    }



    @Named("stringToTimestamp")
    protected Timestamp convertStringToTimestamp(String date) {
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
    protected Integer calculateAge(Timestamp dateOfBirth) {
        if (dateOfBirth == null) {
            return null;
        }

        Date now = new Date();
        long diffInMillis = Math.abs(now.getTime() - dateOfBirth.toDate().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        return (int) (diff / 365);
    }
}
