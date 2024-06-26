package com.match.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.match.domain.entity.AddedPeople;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class AddedPeopleRepository {
    Firestore dbFirestore = FirestoreClient.getFirestore();
    CollectionReference collection = dbFirestore.collection("added_people");

    public List<AddedPeople> getAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = collection.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        return documents.stream()
                .map(x -> x.toObject(AddedPeople.class)).toList();
    }

    public String save(AddedPeople entity) {
        DocumentReference addedDocRef;
        // генерируем юид
        if (entity.getUid() == null) {
            addedDocRef = collection.document();
            entity.setUid(addedDocRef.getId());
        } else {
            addedDocRef = collection.document(entity.getUid());
        }
        ApiFuture<WriteResult> writeResult = addedDocRef.set(entity);

        try {
            writeResult.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return entity.getUid();
    }

    public Optional<AddedPeople> findById(String uid) {
        ApiFuture<DocumentSnapshot> future = collection.document(uid).get();
        DocumentSnapshot document;
        try {
            document = future.get();
            // Возвращаем Optional, который может быть пустым, если document.toObject() возвращает null
            return Optional.ofNullable(document.toObject(AddedPeople.class));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<AddedPeople> findByName(String name) {
        ApiFuture<QuerySnapshot> future = collection.whereEqualTo("name", name).get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            return Optional.of(documents.get(0).toObject(AddedPeople.class));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<AddedPeople> findByAnalyzedBy(String userId) {
        ApiFuture<QuerySnapshot> future = collection.whereEqualTo("userId", userId).get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            return Optional.of(documents.get(0).toObject(AddedPeople.class));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
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
