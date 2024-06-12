package com.match.repository;

import com.match.domain.entity.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepository extends AbstractRepositoryFirebase<UserProfile> {
    protected UserProfileRepository(String collectionName, Class<UserProfile> entityClass) {
        super("user_profiles", entityClass);
    }
}
