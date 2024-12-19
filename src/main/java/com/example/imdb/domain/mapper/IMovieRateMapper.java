package com.example.imdb.domain.mapper;


import com.example.imdb.domain.dto.MovieRateResponse;
import com.example.imdb.domain.entities.TitleRatings;

public interface IMovieRateMapper {
    MovieRateResponse toResponse(TitleRatings entity);
}
