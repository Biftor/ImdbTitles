package com.example.imdb.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieCrewResponse {
    private String[] directors;
    private String[] writers;
    private MovieResponse movie;
}
