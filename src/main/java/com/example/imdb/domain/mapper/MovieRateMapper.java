package com.example.imdb.domain.mapper;

import com.example.imdb.domain.dto.MovieRateResponse;
import com.example.imdb.domain.entities.TitleRatings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieRateMapper implements IMovieRateMapper {
    private final IMovieMapper movieMapper;
    @Override
    public MovieRateResponse toResponse(TitleRatings entity) {
        return MovieRateResponse.builder()
                .averageRating(entity.getAverageRating())
                .numVotes(entity.getNumVotes())
                .movie(movieMapper.toResponse(entity.getTitleBasics()))
                .build();
    }
}
