package com.example.imdb.domain.mapper;

import com.example.imdb.domain.dto.MovieCrewResponse;
import com.example.imdb.domain.entities.TitleCrew;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieCrewMapper implements IMovieCrewMapper {
    private final IMovieMapper movieMapper;
    @Override
    public MovieCrewResponse toResponse(TitleCrew entity) {
        return MovieCrewResponse.builder()
                .writers(entity.getWriters())
                .directors(entity.getDirectors())
                .movie(movieMapper.toMovieActorsResponse(entity.getTitleBasics()))
                .build();
    }
}
