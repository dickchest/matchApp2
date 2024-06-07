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

    public String save(UserProfile entity) {
        DocumentReference addedDocRef = null;
        // проверка, есть ли документ
        if (get(entity.getId()) == null) {
            // тогда создаем новую запись в бд
            addedDocRef = collection.document();
            entity.setId(addedDocRef.getId());
        } else {
            // иначе достаем имеююся запись
            addedDocRef = collection.document(entity.getId());
        }

        ApiFuture<WriteResult> writeResult = addedDocRef.set(entity);
        return addedDocRef.getId();
    }

    public UserProfile get(String documentId) {
        DocumentSnapshot document = checkIfExistDocument(documentId);
        return document.toObject(UserProfile.class);
    }


    public String delete(String documentId) {
        // нужно проверить, есть ли документ
        DocumentSnapshot document = checkIfExistDocument(documentId);
        ApiFuture<WriteResult> collectionsApiFuture = collection.document(documentId).delete();
        return "Successfully deleted " + documentId;
    }

    private DocumentSnapshot checkIfExistDocument(String documentId) {
        DocumentReference documentReference = collection.document(documentId);
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
