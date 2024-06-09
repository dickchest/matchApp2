package com.match.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.match.domain.Users;
import com.match.domain.enums.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Repository
public class UserStatusRepository {
    Firestore dbFirestore = FirestoreClient.getFirestore();
    CollectionReference collection = dbFirestore.collection("users");

    public void save(String uid, UserStatus status) {
        Map<String, String> userData = new HashMap<>();
        userData.put("status", status.toString());

        ApiFuture<WriteResult> future = collection.document(uid).set(userData);
        try{
            future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserStatus getStatus(String uid) {
        DocumentReference documentReference = collection.document(uid);
        DocumentSnapshot document = null;
        try {
            document = documentReference.get().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return Objects.requireNonNull(document.toObject(Users.class)).getStatus();
    }
}
