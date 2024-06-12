package com.match.repository;

import com.match.domain.entity.Language;
import org.springframework.stereotype.Repository;

@Repository
public class LanguagesRepository extends AbstractRepositoryFirebase<Language> {

    public LanguagesRepository() {
        super("languages", Language.class);
    }
//    Firestore dbFirestore = FirestoreClient.getFirestore();
//    CollectionReference collection = dbFirestore.collection("languages");
//
//    public List<Language> getAll() throws ExecutionException, InterruptedException {
//        ApiFuture<QuerySnapshot> future = collection.get();
//        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//
//        return documents.stream()
//                .map(x -> x.toObject(Language.class)).toList();
//    }
//
//    public void save(Language entity) {
//        ApiFuture<WriteResult> writeResult = collection.document(entity.getUid()).set(entity);
//        try {
//            writeResult.get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Optional<Language> findById(String uid) {
//        ApiFuture<DocumentSnapshot> future = collection.document(uid).get();
//        DocumentSnapshot document;
//        try {
//            document = future.get();
//            // Возвращаем Optional, который может быть пустым, если document.toObject() возвращает null
//            return Optional.ofNullable(document.toObject(Language.class));
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Optional<Language> findByName(String name) {
//        ApiFuture<QuerySnapshot> future = collection.whereEqualTo("name", name).get();
//        try {
//            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//            return Optional.ofNullable(documents.get(0).toObject(Language.class));
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public String delete(String uid) {
//        // нужно проверить, есть ли документ
//        DocumentSnapshot document = checkIfExistDocument(uid);
//        ApiFuture<WriteResult> collectionsApiFuture = collection.document(uid).delete();
//        return "Successfully deleted " + uid;
//    }
//
//    private DocumentSnapshot checkIfExistDocument(String uid) {
//        DocumentReference documentReference = collection.document(uid);
//        ApiFuture<DocumentSnapshot> future = documentReference.get();
//        try {
//            DocumentSnapshot document = future.get();
//            if (document.exists()) {
//                return document;
//            } else {
//                throw new RuntimeException("Entity Not Found");
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
