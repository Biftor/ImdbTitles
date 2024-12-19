package com.example.imdb.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieRateResponse {
    private BigDecimal averageRating;
    private Integer numVotes;
    private MovieResponse movie;
}
