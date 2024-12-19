package com.example.imdb.domain.mapper;

import com.example.imdb.domain.dto.MovieActorsResponse;
import com.example.imdb.domain.dto.MovieResponse;
import com.example.imdb.domain.entities.TitleBasics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieMapper implements IMovieMapper {
    private final INameMapper nameMapper;
    @Override
    public MovieResponse toResponse(TitleBasics titleBasics) {
        return MovieResponse.builder()
                .tconst(titleBasics.getTconst())
                .titleType(titleBasics.getTitleType())
                .primaryTitle(titleBasics.getPrimaryTitle())
                .originalTitle(titleBasics.getOriginalTitle())
                .isAdult(titleBasics.getIsAdult())
                .startYear(titleBasics.getStartYear())
                .endYear(titleBasics.getEndYear())
                .runtimeMinutes(titleBasics.getRuntimeMinutes())
                .genres(titleBasics.getGenres())
                .build();
    }

    @Override
    public MovieActorsResponse toMovieActorsResponse(TitleBasics titleBasics) {
        return MovieActorsResponse.builder()
                .tconst(titleBasics.getTconst())
                .titleType(titleBasics.getTitleType())
                .primaryTitle(titleBasics.getPrimaryTitle())
                .originalTitle(titleBasics.getOriginalTitle())
                .isAdult(titleBasics.getIsAdult())
                .startYear(titleBasics.getStartYear())
                .endYear(titleBasics.getEndYear())
                .runtimeMinutes(titleBasics.getRuntimeMinutes())
                .genres(titleBasics.getGenres())
                .actors(titleBasics.getActors().stream().map(nameMapper::toShortResponse).toList())
                .build();
    }
}
