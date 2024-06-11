package com.match.services.mapping;

import com.match.domain.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import com.match.domain.entity.Language;
import com.match.domain.entity.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class userProfileMapperTestWithMock {
    @Mock
    private LanguagesMapper languagesMapper;

    @InjectMocks
    UserProfileMapper userProfileMapper = Mappers.getMapper(UserProfileMapper.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(languagesMapper.toLanguageFromUid(anyString())).thenAnswer(invocation -> {
            String uid = invocation.getArgument(0);
            return new Language(uid, "SomeCode", "SomeLanguage", "SomeLogoPath");
        });

        when(languagesMapper.toLanguageFromName(anyString())).thenAnswer(invocation -> {
            String name = invocation.getArgument(0);
            return new Language("SomeId", "SomeCode", name, "SomeLogoPath");
        });
    }

    @Test
    void toDto() {
        // подготовка
        UserProfile userProfile = new UserProfile();
        userProfile.setName("John Doe");
        userProfile.setLanguages(List.of("123"));

        // действие
        UserProfileRequestDto dto = userProfileMapper.toDto(userProfile);

        // проверка
        assertEquals(1, dto.getLanguages().size());
        assertEquals("SomeLanguage", dto.getLanguages().get(0));
    }

    @Test
    void fromDto() {
        // подготовка
        UserProfileRequestDto dto = new UserProfileRequestDto();
        dto.setName("John Doe");
        dto.setLanguages(List.of("english"));

        // действие
        UserProfile userProfile = userProfileMapper.fromDto(dto);

        // проверка
        assertEquals(1, userProfile.getLanguages().size());
        assertEquals("SomeId", userProfile.getLanguages().get(0));
    }
}
