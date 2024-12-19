package com.example.imdb.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoviePrincipalsResponse {
    private Integer ordering;
    private String category;
    private String job;
    private String characters;
    private MovieResponse movie;
}
