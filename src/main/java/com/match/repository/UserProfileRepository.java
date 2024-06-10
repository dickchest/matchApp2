package com.match.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.match.domain.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UserProfileRepository {
    Firestore dbFirestore = FirestoreClient.getFirestore();
    CollectionReference collection = dbFirestore.collection("user_profiles");

    public List<UserProfile> getAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = collection.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        return documents.stream()
                .map(x -> x.toObject(UserProfile.class)).toList();
    }

    public void save(UserProfile entity) {
        ApiFuture<WriteResult> writeResult = collection.document(entity.getUserId()).set(entity);

    }

    public UserProfile get(String uid) {
        DocumentSnapshot document = checkIfExistDocument(uid);
        return document.toObject(UserProfile.class);
    }


    public String delete(String uid) {
        // нужно проверить, есть ли документ
        DocumentSnapshot document = checkIfExistDocument(uid);
        ApiFuture<WriteResult> collectionsApiFuture = collection.document(uid).delete();
        return "Successfully deleted " + uid;
    }

    private DocumentSnapshot checkIfExistDocument(String uid) {
        DocumentReference documentReference = collection.document(uid);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document;
            } else {
                throw new RuntimeException("Entity Not Found");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
