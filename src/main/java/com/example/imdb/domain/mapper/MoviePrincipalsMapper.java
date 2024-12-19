package com.example.imdb.domain.mapper;

import com.example.imdb.domain.dto.MoviePrincipalsResponse;
import com.example.imdb.domain.entities.TitlePrincipals;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MoviePrincipalsMapper implements IMoviePrincipalsMapper {
    private final IMovieMapper movieMapper;

    @Override
    public MoviePrincipalsResponse toResponse(TitlePrincipals entity) {
        return MoviePrincipalsResponse.builder()
                .ordering(entity.getOrdering())
                .category(entity.getCategory())
                .job(entity.getJob())
                .characters(entity.getCharacters())
                .movie(movieMapper.toMovieActorsResponse(entity.getTitleBasics()))
                .build();
    }
}
