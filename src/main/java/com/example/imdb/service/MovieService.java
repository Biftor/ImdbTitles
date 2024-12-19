package com.example.imdb.service;

import com.example.imdb.domain.dto.MovieCrewResponse;
import com.example.imdb.domain.dto.MoviePrincipalsResponse;
import com.example.imdb.domain.dto.MovieRateResponse;
import com.example.imdb.domain.mapper.IMovieCrewMapper;
import com.example.imdb.domain.mapper.IMoviePrincipalsMapper;
import com.example.imdb.domain.mapper.IMovieRateMapper;
import com.example.imdb.repository.TitleCrewRepository;
import com.example.imdb.repository.TitlePrincipalsRepository;
import com.example.imdb.repository.TitleRatingsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService{

    private IMovieRateMapper movieRateMapper;
    private IMovieCrewMapper movieCrewMapper;
    private IMoviePrincipalsMapper moviePrincipalsMapper;

    private TitleRatingsRepository titleRatingsRepository;

    private TitleCrewRepository titleCrewRepository;

    private TitlePrincipalsRepository titlePrincipalsRepository;


    @Override
    public Page<MovieCrewResponse> getTitlesSameDirectorAndWriter(Pageable pageable) {
        return titleCrewRepository.findTitlesSameDirectorAndWriter(pageable).map(movieCrewMapper::toResponse);
    }


    @Override
    public Page<MoviePrincipalsResponse> getTitlesTwoActorsByNId(Pageable pageable, String actor1NId, String actor2NId) {
        return titlePrincipalsRepository.findTitlesWithBothActorsByNConst(pageable, actor1NId, actor2NId).map(moviePrincipalsMapper::toResponse);
    }

    @Override
    public Page<MoviePrincipalsResponse> getTitlesTwoActorsByName(Pageable pageable, String actor1Name, String actor2Name) {
        return titlePrincipalsRepository.findTitlesWithBothActorsByName(pageable, actor1Name, actor2Name).map(moviePrincipalsMapper::toResponse);
    }

    @Override
    public List<MovieRateResponse> getBestTitlesOfYearByGenre(String genre) {
        return titleRatingsRepository.getBestRatingsOfYearByGenre(genre).stream().map(movieRateMapper::toResponse).toList();
    }
}