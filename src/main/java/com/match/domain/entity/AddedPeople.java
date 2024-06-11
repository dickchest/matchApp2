package com.match.domain.entity;

import com.google.cloud.Timestamp;
import lombok.*;

@Data
@Builder
public class AddedPeople {
    private String uid; // выбирается автоматически
    private String userId; // какой user вводил запрос на анализ этого человека
    private String photo;
    private String name;
    private Timestamp dateOfBirth;
    private Integer gender;
    private String relationshipType;
}
