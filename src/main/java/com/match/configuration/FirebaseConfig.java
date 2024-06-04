package com.match.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class FirebaseConfig {
    @Value("classpath:serviceAccountKey.json")
    Resource resourceFile;

    @Bean
    public FirebaseAuth firebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public void initializeFirebaseApp() throws IOException {

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(resourceFile.getInputStream()))
                .setServiceAccountId("firebase-adminsdk-9r217@match-app-2.iam.gserviceaccount.com")
                .build();

        FirebaseApp.initializeApp(options);
    }



}
