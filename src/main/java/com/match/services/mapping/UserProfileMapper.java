package com.match.services.mapping;

import com.google.cloud.Timestamp;
import com.match.domain.Languages;
import com.match.domain.UserProfile;
import com.match.dto.myselfDtos.basic.UserProfileBasicResponseDto;
import com.match.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import org.mapstruct.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

//    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "languages", source = "languages", qualifiedByName = "languagesToNames")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "timestampToString")
    UserProfileRequestDto toDto(UserProfile userProfile);

    @Mapping(target = "age", source = "dateOfBirth", qualifiedByName = "calculateAgeFromTimestamp")
    UserProfileBasicResponseDto toBasicResponseDto(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "languages", source = "languages", qualifiedByName = "namesToLanguages")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToTimestamp")
    UserProfile fromDto(UserProfileRequestDto userProfileRequestDto);

    @Named("languagesToNames")
    default List<String> mapLanguagesToNames(List<Languages> languages) {
        if (languages == null) {
            return null;
        }
        return languages.stream()
                .map(Languages::getName)
                .collect(Collectors.toList());
    }

    @Named("namesToLanguages")
    default List<Languages> mapNamesToLanguages(List<String> names) {
        if (names == null) {
            return null;
        }
        return names.stream()
                .map(name -> {
                    Languages language = new Languages();
                    language.setName(name);
                    // todo вставить проверку, есть ли такой язык в базе данных
                    // и подтягивать все поля из базы данных о языке

                    // тут вставить логику для установления кода и лога
                    return language;
                })
                .collect(Collectors.toList());
    }

    @Named("timestampToString")
    default String convertTimestampToString(Timestamp timestamp) {
        return timestamp != null ? dateFormat.format(timestamp) : null;
    }

    @Named("stringToTimestamp")
    default Timestamp convertStringToTimestamp(String date) {
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
