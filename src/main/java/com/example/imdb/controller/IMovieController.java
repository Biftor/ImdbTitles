package com.example.imdb.controller;

import com.example.imdb.domain.dto.MovieCrewResponse;
import com.example.imdb.domain.dto.MoviePrincipalsResponse;
import com.example.imdb.domain.dto.MovieRateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("imdb")
public interface IMovieController {

    @GetMapping("/same-actor-writer")
    ResponseEntity<Page<MovieCrewResponse>> getSameActorWriter(Pageable pageable);

    @GetMapping("/two-actors-id")
    ResponseEntity<Page<MoviePrincipalsResponse>> getTitlesWithTwoActorsNConst(Pageable pageable, @RequestParam String actorId1, @RequestParam String actorId2);

    @GetMapping("/two-actors-name")
    ResponseEntity<Page<MoviePrincipalsResponse>> getTitlesWithTwoActorsName(Pageable pageable, @RequestParam String actorName1, @RequestParam String actorName2);

    @GetMapping("/best-by-genre")
    ResponseEntity<List<MovieRateResponse>> getBestTitlesOfYearByGenre(@RequestParam String genre);

    @GetMapping("/request-count")
    long getRequestCount();
}
