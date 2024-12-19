package com.example.imdb.controller;

import com.example.imdb.config.RequestCounterFilter;
import com.example.imdb.domain.dto.MovieCrewResponse;
import com.example.imdb.domain.dto.MoviePrincipalsResponse;
import com.example.imdb.domain.dto.MovieRateResponse;
import com.example.imdb.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class MovieController implements IMovieController{

    private final MovieService movieService;

    @Override
    public ResponseEntity<Page<MovieCrewResponse>> getSameActorWriter(Pageable pageable) {
        return ResponseEntity.ok(movieService.getTitlesSameDirectorAndWriter(pageable));
    }

    @Override
    public ResponseEntity<Page<MoviePrincipalsResponse>> getTitlesWithTwoActorsNConst(Pageable pageable, String actorId1, String actorId2) {
        return ResponseEntity.ok(movieService.getTitlesTwoActorsByNId(pageable, actorId1, actorId2));
    }

    @Override
    public ResponseEntity<Page<MoviePrincipalsResponse>> getTitlesWithTwoActorsName(Pageable pageable, String actorName1, String actorName2) {
        return ResponseEntity.ok(movieService.getTitlesTwoActorsByName(pageable, actorName1, actorName2));
    }

    @Override
    public ResponseEntity<List<MovieRateResponse>> getBestTitlesOfYearByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(movieService.getBestTitlesOfYearByGenre(genre));
    }

    @Override
    public long getRequestCount() {
        return RequestCounterFilter.getRequestCount();
    }

}