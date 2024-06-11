package com.match.services.mapping;

import com.match.domain.Languages;
import com.match.domain.UserProfile;
import com.match.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserProfileMapper {

    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    @Mapping(target = "languages", source = "languages", qualifiedByName = "languagesToNames")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    UserProfileDto toDto(UserProfile userProfile);

    @Mapping(target = "languages", source = "languages", qualifiedByName = "namesToLanguages")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    UserProfile fromDto(UserProfileDto userProfileDto);

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
                    // тут вставить логику для установления кода и лога
                    return language;
                })
                .collect(Collectors.toList());
    }
}