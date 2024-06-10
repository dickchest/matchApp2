package com.match.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Language {
    private String uid;
    private String code;
    private String name;
    private String logo;
}
