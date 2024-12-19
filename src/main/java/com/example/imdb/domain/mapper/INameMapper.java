package com.example.imdb.domain.mapper;


import com.example.imdb.domain.dto.NameResponse;
import com.example.imdb.domain.dto.NameWithMoviesResponse;
import com.example.imdb.domain.dto.ShortNameResponse;
import com.example.imdb.domain.entities.NameBasics;

public interface INameMapper {
    NameResponse toResponse(NameBasics nameBasics);
    ShortNameResponse toShortResponse(NameBasics nameBasics);
    NameWithMoviesResponse toWithMovieResponse(NameBasics nameBasics);
}
