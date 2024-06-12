package com.match.domain.entity;

import com.google.cloud.Timestamp;
import lombok.*;


@Getter
@Setter
@Builder
public class AddedPeople extends BaseEntity{
    private String uid; // выбирается автоматически
    private String userId; // какой user вводил запрос на анализ этого человека
    private String photo;
    private String name;
    private Timestamp dateOfBirth;
    private Integer gender;
    private String relationshipType;
}
