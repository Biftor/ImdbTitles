package com.example.imdb.domain.mapper;

import com.example.imdb.domain.dto.NameResponse;
import com.example.imdb.domain.dto.NameWithMoviesResponse;
import com.example.imdb.domain.dto.ShortNameResponse;
import com.example.imdb.domain.entities.NameBasics;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class NameMapper implements INameMapper {
//    private final IMovieMapper movieMapper;
    @Override
    public NameResponse toResponse(NameBasics nameBasics) {
        return NameResponse.builder()
                .nconst(nameBasics.getNconst())
                .primaryName(nameBasics.getPrimaryName())
                .birthYear(nameBasics.getBirthYear())
                .deathYear(nameBasics.getDeathYear())
                .primaryProfession(nameBasics.getPrimaryProfession())
                .knownForTitles(nameBasics.getKnownForTitles())
                .build();
    }

    @Override
    public ShortNameResponse toShortResponse(NameBasics nameBasics) {
        return ShortNameResponse.builder()
                .nconst(nameBasics.getNconst())
                .primaryName(nameBasics.getPrimaryName())
                .build();
    }

    @Override
    public NameWithMoviesResponse toWithMovieResponse(NameBasics nameBasics) {
        return NameWithMoviesResponse.builder()
                .nconst(nameBasics.getNconst())
                .primaryName(nameBasics.getPrimaryName())
                .birthYear(nameBasics.getBirthYear())
                .deathYear(nameBasics.getDeathYear())
                .primaryProfession(nameBasics.getPrimaryProfession())
                .knownForTitles(nameBasics.getKnownForTitles())
                .movies(new ArrayList<>())
                //.movies(nameBasics.getMovies().stream().map(movieMapper::toResponse).toList())
                .build();
    }
}
