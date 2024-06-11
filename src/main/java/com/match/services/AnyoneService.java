package com.match.services;

import com.match.domain.dto.UserStatusDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service
public class AnyoneService {

    public UserStatusDto analyze(MultipartFile photo, String name, String dateOfBirth, Integer gender, String relationshipType, Principal principal) {
        return null;
    }
}
