package com.match.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Repository;

import java.security.Principal;

@Repository
public class AuthRepository {
    public String getUserUid(Principal principal) {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(principal.getName());
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        return principal.getName();
    }
}
