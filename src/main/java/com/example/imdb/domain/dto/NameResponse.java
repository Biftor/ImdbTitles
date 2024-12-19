package com.example.imdb.domain.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class NameResponse extends ShortNameResponse {
    private Integer birthYear;
    private Integer deathYear;
    private String[] primaryProfession;
    private String[] knownForTitles;
}
