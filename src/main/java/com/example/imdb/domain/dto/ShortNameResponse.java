package com.example.imdb.domain.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortNameResponse {
    private String nconst;
    private String primaryName;
}
