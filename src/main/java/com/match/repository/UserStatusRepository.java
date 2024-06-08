package com.match.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.match.domain.enums.userStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserStatusRepository {
    Firestore dbFirestore = FirestoreClient.getFirestore();
    CollectionReference collection = dbFirestore.collection("users");

    public void save(String uid, userStatus status) {
        Map<String, String> userData = new HashMap<>();
        userData.put("status", status.toString());

        ApiFuture<WriteResult> future = collection.document(uid).set(userData);
        try{
            future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
