package com.example.imdb.domain.mapper;

import com.example.imdb.domain.dto.MoviePrincipalsResponse;
import com.example.imdb.domain.entities.TitlePrincipals;

public interface IMoviePrincipalsMapper {
    MoviePrincipalsResponse toResponse(TitlePrincipals entity);
}
