package com.example.imdb.domain.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class NameWithMoviesResponse extends NameResponse{
    private List<MovieResponse> movies;
}
