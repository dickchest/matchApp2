package com.match.services.mapping;

import com.match.domain.Languages;
import com.match.domain.UserProfile;
import com.match.domain.UserProfileQuestions;
import com.match.dto.UserProfileDto;
import com.match.dto.UserProfileQuestionsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserProfileMapperTest {
    private UserProfileMapper userProfileMapper;

    @BeforeEach
    void setUp() {
        userProfileMapper = Mappers.getMapper(UserProfileMapper.class);
    }

    @Test
    void toDto() {
        // Подготовка
        UserProfile userProfile = new UserProfile();
        userProfile.setName("John Doe");
        userProfile.setDateOfBirth(LocalDate.of(1990, 1, 1));
        userProfile.setGender(1);
        userProfile.setCity("New York");
        userProfile.setGoals(Arrays.asList(1, 2, 3));
        userProfile.setGoalsShow(true);
        userProfile.setGenderLook(Arrays.asList(1));
        userProfile.setGenderLookShow(true);
        userProfile.setValues(Arrays.asList("Honesty", "Respect"));
        userProfile.setQuestions(new UserProfileQuestions()); // Заполните, если в объекте есть данные
        userProfile.setToddlers(Collections.singletonMap("child1", 5));
        userProfile.setInterests(Arrays.asList("Reading", "Traveling"));
        // Языки
        userProfile.setLanguages(Arrays.asList(
                new Languages("EN", "English", "logoPath"),
                new Languages("FR", "French", "logoPath")));
        // Вопросы
        userProfile.setQuestions(new UserProfileQuestions(
                "city", "beach", Arrays.asList("romantically", "spontaneously"), "vegetarian", "museums", "reading", "morning", "indoors", "relaxing"));


        // Действие
        UserProfileDto userProfileDto = userProfileMapper.toDto(userProfile);

        // проверка
        assertNotNull(userProfileDto);
        assertEquals(userProfile.getName(), userProfileDto.getName());
        assertEquals("01.01.1990", userProfileDto.getDateOfBirth());
        assertEquals(userProfile.getGender(), userProfileDto.getGender());
        assertEquals(userProfile.getCity(), userProfileDto.getCity());
        assertEquals(userProfile.getGoals(), userProfileDto.getGoals());
        assertEquals(userProfile.getGoalsShow(), userProfileDto.getGoalsShow());
        assertEquals(userProfile.getGenderLook(), userProfileDto.getGenderLook());
        assertEquals(userProfile.getGenderLookShow(), userProfileDto.getGenderLookShow());
        assertEquals(userProfile.getValues(), userProfileDto.getValues());
        assertEquals(userProfile.getToddlers(), userProfileDto.getToddlers());
        assertEquals(userProfile.getInterests(), userProfileDto.getInterests());
        // проверка языков
        assertEquals(2, userProfileDto.getLanguages().size());
        assertEquals("English", userProfileDto.getLanguages().get(0));
        assertEquals("French", userProfileDto.getLanguages().get(1));
        // проверка вопросов
        assertNotNull(userProfileDto.getQuestions());
        assertEquals(userProfile.getQuestions().getPreferToLive(), userProfileDto.getQuestions().getPreferToLive());
        assertEquals(userProfile.getQuestions().getIdealVacation(), userProfileDto.getQuestions().getIdealVacation());
        assertEquals(userProfile.getQuestions().getHowDoYouLove(), userProfileDto.getQuestions().getHowDoYouLove());
        assertEquals(userProfile.getQuestions().getPreferToEat(), userProfileDto.getQuestions().getPreferToEat());
        assertEquals(userProfile.getQuestions().getPreferToVisit(), userProfileDto.getQuestions().getPreferToVisit());
        assertEquals(userProfile.getQuestions().getAfterWork(), userProfileDto.getQuestions().getAfterWork());
        assertEquals(userProfile.getQuestions().getWhatIsBest(), userProfileDto.getQuestions().getWhatIsBest());
        assertEquals(userProfile.getQuestions().getGenerallyPrefer(), userProfileDto.getQuestions().getGenerallyPrefer());
        assertEquals(userProfile.getQuestions().getSpendTheDay(), userProfileDto.getQuestions().getSpendTheDay());
    }

    @Test
    void fromDto() {
        // Подготовка
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setName("John Doe");
        userProfileDto.setDateOfBirth("01.01.1990");
        userProfileDto.setGender(1);
        userProfileDto.setCity("New York");
        userProfileDto.setGoals(Arrays.asList(1, 2, 3));
        userProfileDto.setGoalsShow(true);
        userProfileDto.setGenderLook(Arrays.asList(1));
        userProfileDto.setGenderLookShow(true);
        userProfileDto.setValues(Arrays.asList("Honesty", "Respect"));
        userProfileDto.setToddlers(Collections.singletonMap("child1", 5));
        userProfileDto.setInterests(Arrays.asList("Reading", "Traveling"));
        // Языки
        userProfileDto.setLanguages(List.of("Spanish", "German"));
        // Вопросы
        userProfileDto.setQuestions(new UserProfileQuestionsDto(
                "city", "beach", Arrays.asList("romantically", "spontaneously"), "vegetarian", "museums", "reading", "morning", "indoors", "relaxing"));

        // Действие
        UserProfile userProfile = userProfileMapper.fromDto(userProfileDto);

        // Проверка
        assertNotNull(userProfile);
        assertEquals(userProfileDto.getName(), userProfile.getName());
        assertEquals(LocalDate.of(1990, 1, 1), userProfile.getDateOfBirth());
        assertEquals(userProfileDto.getGender(), userProfile.getGender());
        assertEquals(userProfileDto.getCity(), userProfile.getCity());
        assertEquals(userProfileDto.getGoals(), userProfile.getGoals());
        assertEquals(userProfileDto.getGoalsShow(), userProfile.getGoalsShow());
        assertEquals(userProfileDto.getGenderLook(), userProfile.getGenderLook());
        assertEquals(userProfileDto.getGenderLookShow(), userProfile.getGenderLookShow());
        assertEquals(userProfileDto.getValues(), userProfile.getValues());
        assertEquals(userProfileDto.getToddlers(), userProfile.getToddlers());
        assertEquals(userProfileDto.getInterests(), userProfile.getInterests());
        // Проверка языков
        assertEquals(2, userProfile.getLanguages().size());
        assertEquals("Spanish", userProfile.getLanguages().get(0).getName());
        assertEquals("German", userProfile.getLanguages().get(1).getName());
        // Проверка вопросов
        assertNotNull(userProfile.getQuestions());
        assertEquals(userProfileDto.getQuestions().getPreferToLive(), userProfile.getQuestions().getPreferToLive());
        assertEquals(userProfileDto.getQuestions().getIdealVacation(), userProfile.getQuestions().getIdealVacation());
        assertEquals(userProfileDto.getQuestions().getHowDoYouLove(), userProfile.getQuestions().getHowDoYouLove());
        assertEquals(userProfileDto.getQuestions().getPreferToEat(), userProfile.getQuestions().getPreferToEat());
        assertEquals(userProfileDto.getQuestions().getPreferToVisit(), userProfile.getQuestions().getPreferToVisit());
        assertEquals(userProfileDto.getQuestions().getAfterWork(), userProfile.getQuestions().getAfterWork());
        assertEquals(userProfileDto.getQuestions().getWhatIsBest(), userProfile.getQuestions().getWhatIsBest());
        assertEquals(userProfileDto.getQuestions().getGenerallyPrefer(), userProfile.getQuestions().getGenerallyPrefer());
        assertEquals(userProfileDto.getQuestions().getSpendTheDay(), userProfile.getQuestions().getSpendTheDay());
    }
}