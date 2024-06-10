package com.match.repository;

import com.match.domain.Language;
import com.match.validation.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LanguageRepositoryTest {
    @Autowired
    private final LanguagesRepository repository;

    LanguageRepositoryTest(LanguagesRepository repository) {
        this.repository = repository;
    }

    @Test
    void findEntityByName() {
        String name = "english";

        Language language = repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found: " + name));

        assertEquals("english", language.getName() );



    }
}