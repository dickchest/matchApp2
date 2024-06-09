package com.match.services.utils.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGeneration {

//    private final RestTemplate restTemplate;

    //    @Value("${firebase.api-key}")
    private final String firebaseApiKey;

    public TokenGeneration(@Value("${firebase.api-key}") String firebaseApiKey) {
//        this.restTemplate = restTemplate;
        this.firebaseApiKey = firebaseApiKey;
    }

    public Map<String, String> generate(UserRecord userRecord) {
        RestTemplate restTemplate = new RestTemplate();

        // Генерация Firebase Token
        String customToken = null;
        try {
            customToken = FirebaseAuth.getInstance().createCustomToken(userRecord.getUid());
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken")
                .queryParam("key", firebaseApiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("token", customToken);
        body.put("returnSecureToken", "true");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(builder.toUriString(), request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, String> responseBody = response.getBody();
            Map<String, String> tokens = new HashMap<>();
            tokens.put("access", responseBody.get("idToken"));
            tokens.put("refresh", responseBody.get("refreshToken"));

            return tokens;
        } else {
            throw new RuntimeException("Failed to exchange custom token for ID and refresh token");
        }
    }
}
