package com.match.services;

import com.match.domain.dto.UserStatusDto;
import com.match.domain.entity.AddedPeople;
import com.match.repository.AddedPeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service
@AllArgsConstructor
public class AnyoneService {
    private final AddedPeopleRepository addedPeopleRepository;

    public UserStatusDto analyze(MultipartFile photo, String name, String dateOfBirth, Integer gender, String relationshipType, Principal principal) {
        AddedPeople entity = new AddedPeople();

        return null;
    }
}
