package com.example.imdb.service;

import com.example.imdb.domain.entities.TitleBasics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ITitleBasicService {
    Page<TitleBasics> getTitlesSameDirectorAndWriter(Pageable pageable);

    Page<TitleBasics> getTitlesTwoActorsByNId(Pageable pageable, String actor1NId, String actor2NId);

    Page<TitleBasics> getTitlesTwoActorsByName(Pageable pageable, String actor1Name, String actor2Name);

    List<TitleBasics> getBestTitlesOfYearByGenre(String genre);
}
