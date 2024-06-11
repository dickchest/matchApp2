package com.match.domain.entity;

import com.google.cloud.Timestamp;
import lombok.*;

@Data
public class AddedPeople {
    private String id; // выбирается автоматически
    private String analyzedBy; // какой user вводил запрос на анализ этого человека
    private String photo;
    private String name;
    private Timestamp dateOfBirth;
    private Integer gender;
}
