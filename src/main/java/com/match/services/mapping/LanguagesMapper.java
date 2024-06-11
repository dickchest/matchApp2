package com.match.services.mapping;

import com.match.domain.entity.Language;
import com.match.repository.LanguagesRepository;
import com.match.validation.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LanguagesMapper {

    private final LanguagesRepository languagesRepository;

    public Language toLanguageFromUid(String  uid) {
        return languagesRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public Language toLanguageFromName(String  name) {
        return languagesRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}
