package com.match.services.mapping;

import com.google.cloud.Timestamp;
import com.match.domain.Language;
import com.match.domain.UserProfile;
import com.match.domain.UserProfileQuestions;
import com.match.dto.myselfDtos.createAndModify.UserProfileRequestDto;
import com.match.dto.myselfDtos.UserProfileQuestionsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    void toDto() throws ParseException {
        // Подготовка
        UserProfile userProfile = new UserProfile();
        userProfile.setName("John Doe");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Timestamp timestamp = Timestamp.of(dateFormat.parse("01.01.1990"));
        userProfile.setDateOfBirth(Timestamp.of(timestamp.toSqlTimestamp()));
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
                new Language("EN", "English", "logoPath"),
                new Language("FR", "French", "logoPath")));
        // Вопросы
        userProfile.setQuestions(new UserProfileQuestions(
                "city", "beach", Arrays.asList("romantically", "spontaneously"), "vegetarian", "museums", "reading", "morning", "indoors", "relaxing"));


        // Действие
        UserProfileRequestDto userProfileRequestDto = userProfileMapper.toDto(userProfile);

        // проверка
        assertNotNull(userProfileRequestDto);
        assertEquals(userProfile.getName(), userProfileRequestDto.getName());
        assertEquals("01.01.1990", userProfileRequestDto.getDateOfBirth());
        assertEquals(userProfile.getGender(), userProfileRequestDto.getGender());
        assertEquals(userProfile.getCity(), userProfileRequestDto.getCity());
        assertEquals(userProfile.getGoals(), userProfileRequestDto.getGoals());
        assertEquals(userProfile.getGoalsShow(), userProfileRequestDto.getGoalsShow());
        assertEquals(userProfile.getGenderLook(), userProfileRequestDto.getGenderLook());
        assertEquals(userProfile.getGenderLookShow(), userProfileRequestDto.getGenderLookShow());
        assertEquals(userProfile.getValues(), userProfileRequestDto.getValues());
        assertEquals(userProfile.getToddlers(), userProfileRequestDto.getToddlers());
        assertEquals(userProfile.getInterests(), userProfileRequestDto.getInterests());
        // проверка языков
        assertEquals(2, userProfileRequestDto.getLanguages().size());
        assertEquals("English", userProfileRequestDto.getLanguages().get(0));
        assertEquals("French", userProfileRequestDto.getLanguages().get(1));
        // проверка вопросов
        assertNotNull(userProfileRequestDto.getQuestions());
        assertEquals(userProfile.getQuestions().getPreferToLive(), userProfileRequestDto.getQuestions().getPreferToLive());
        assertEquals(userProfile.getQuestions().getIdealVacation(), userProfileRequestDto.getQuestions().getIdealVacation());
        assertEquals(userProfile.getQuestions().getHowDoYouLove(), userProfileRequestDto.getQuestions().getHowDoYouLove());
        assertEquals(userProfile.getQuestions().getPreferToEat(), userProfileRequestDto.getQuestions().getPreferToEat());
        assertEquals(userProfile.getQuestions().getPreferToVisit(), userProfileRequestDto.getQuestions().getPreferToVisit());
        assertEquals(userProfile.getQuestions().getAfterWork(), userProfileRequestDto.getQuestions().getAfterWork());
        assertEquals(userProfile.getQuestions().getWhatIsBest(), userProfileRequestDto.getQuestions().getWhatIsBest());
        assertEquals(userProfile.getQuestions().getGenerallyPrefer(), userProfileRequestDto.getQuestions().getGenerallyPrefer());
        assertEquals(userProfile.getQuestions().getSpendTheDay(), userProfileRequestDto.getQuestions().getSpendTheDay());
    }

    @Test
    void fromDto() {
        // Подготовка
        UserProfileRequestDto userProfileRequestDto = new UserProfileRequestDto();
        userProfileRequestDto.setName("John Doe");
        userProfileRequestDto.setDateOfBirth("01.01.1990");
        userProfileRequestDto.setGender(1);
        userProfileRequestDto.setCity("New York");
        userProfileRequestDto.setGoals(Arrays.asList(1, 2, 3));
        userProfileRequestDto.setGoalsShow(true);
        userProfileRequestDto.setGenderLook(Arrays.asList(1));
        userProfileRequestDto.setGenderLookShow(true);
        userProfileRequestDto.setValues(Arrays.asList("Honesty", "Respect"));
        userProfileRequestDto.setToddlers(Collections.singletonMap("child1", 5));
        userProfileRequestDto.setInterests(Arrays.asList("Reading", "Traveling"));
        // Языки
        userProfileRequestDto.setLanguages(List.of("Spanish", "German"));
        // Вопросы
        userProfileRequestDto.setQuestions(new UserProfileQuestionsDto(
                "city", "beach", Arrays.asList("romantically", "spontaneously"), "vegetarian", "museums", "reading", "morning", "indoors", "relaxing"));

        // Действие
        UserProfile userProfile = userProfileMapper.fromDto(userProfileRequestDto);

        // Проверка
        assertNotNull(userProfile);
        assertEquals(userProfileRequestDto.getName(), userProfile.getName());
        assertEquals(LocalDate.of(1990, 1, 1), userProfile.getDateOfBirth());
        assertEquals(userProfileRequestDto.getGender(), userProfile.getGender());
        assertEquals(userProfileRequestDto.getCity(), userProfile.getCity());
        assertEquals(userProfileRequestDto.getGoals(), userProfile.getGoals());
        assertEquals(userProfileRequestDto.getGoalsShow(), userProfile.getGoalsShow());
        assertEquals(userProfileRequestDto.getGenderLook(), userProfile.getGenderLook());
        assertEquals(userProfileRequestDto.getGenderLookShow(), userProfile.getGenderLookShow());
        assertEquals(userProfileRequestDto.getValues(), userProfile.getValues());
        assertEquals(userProfileRequestDto.getToddlers(), userProfile.getToddlers());
        assertEquals(userProfileRequestDto.getInterests(), userProfile.getInterests());
        // Проверка языков
        assertEquals(2, userProfile.getLanguages().size());
        assertEquals("Spanish", userProfile.getLanguages().get(0).getName());
        assertEquals("German", userProfile.getLanguages().get(1).getName());
        // Проверка вопросов
        assertNotNull(userProfile.getQuestions());
        assertEquals(userProfileRequestDto.getQuestions().getPreferToLive(), userProfile.getQuestions().getPreferToLive());
        assertEquals(userProfileRequestDto.getQuestions().getIdealVacation(), userProfile.getQuestions().getIdealVacation());
        assertEquals(userProfileRequestDto.getQuestions().getHowDoYouLove(), userProfile.getQuestions().getHowDoYouLove());
        assertEquals(userProfileRequestDto.getQuestions().getPreferToEat(), userProfile.getQuestions().getPreferToEat());
        assertEquals(userProfileRequestDto.getQuestions().getPreferToVisit(), userProfile.getQuestions().getPreferToVisit());
        assertEquals(userProfileRequestDto.getQuestions().getAfterWork(), userProfile.getQuestions().getAfterWork());
        assertEquals(userProfileRequestDto.getQuestions().getWhatIsBest(), userProfile.getQuestions().getWhatIsBest());
        assertEquals(userProfileRequestDto.getQuestions().getGenerallyPrefer(), userProfile.getQuestions().getGenerallyPrefer());
        assertEquals(userProfileRequestDto.getQuestions().getSpendTheDay(), userProfile.getQuestions().getSpendTheDay());
    }
}