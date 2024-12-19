package com.example.imdb.domain.mapper;


import com.example.imdb.domain.dto.MovieCrewResponse;
import com.example.imdb.domain.entities.TitleCrew;

public interface IMovieCrewMapper {
    MovieCrewResponse toResponse(TitleCrew entity);
}
