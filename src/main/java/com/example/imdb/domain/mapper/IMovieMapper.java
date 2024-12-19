package com.example.imdb.domain.mapper;


import com.example.imdb.domain.dto.MovieActorsResponse;
import com.example.imdb.domain.dto.MovieResponse;
import com.example.imdb.domain.entities.TitleBasics;

public interface IMovieMapper {
    MovieResponse toResponse(TitleBasics titleBasics);
    MovieActorsResponse toMovieActorsResponse(TitleBasics titleBasics);
}
