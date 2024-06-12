package com.match.domain.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Language extends BaseEntity{
    private String uid;
    private String code;
    private String name;
    private String logo;
}
