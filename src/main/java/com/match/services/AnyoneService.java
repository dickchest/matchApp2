package com.match.services;

import com.match.dto.UserStatusDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service
public class AnyoneService {
    public UserStatusDto analyze(MultipartFile photo, Principal principal) {
        return null;
    }
}
