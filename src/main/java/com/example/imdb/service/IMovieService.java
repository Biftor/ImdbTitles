package com.example.imdb.service;

import com.example.imdb.domain.dto.MovieCrewResponse;
import com.example.imdb.domain.dto.MoviePrincipalsResponse;
import com.example.imdb.domain.dto.MovieRateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IMovieService {
    Page<MovieCrewResponse> getTitlesSameDirectorAndWriter(Pageable pageable);

    Page<MoviePrincipalsResponse> getTitlesTwoActorsByNId(Pageable pageable, String actor1NId, String actor2NId);

    Page<MoviePrincipalsResponse> getTitlesTwoActorsByName(Pageable pageable, String actor1Name, String actor2Name);

    List<MovieRateResponse> getBestTitlesOfYearByGenre(String genre);
}
