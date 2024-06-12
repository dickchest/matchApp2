package com.match.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.match.domain.entity.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UserProfileRepository extends AbstractRepositoryFirebase<UserProfile>{

    public UserProfileRepository() {
        super("user_profiles", UserProfile.class);
    }

}
