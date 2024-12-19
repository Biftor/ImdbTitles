package com.example.imdb.service;

import com.example.imdb.domain.entities.TitleBasics;
import com.example.imdb.repository.TitleBasicsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TitleBasicService implements ITitleBasicService{
    private TitleBasicsRepository titleBasicsRepository;

    @Override
    public Page<TitleBasics> getTitlesSameDirectorAndWriter(Pageable pageable) {
        return titleBasicsRepository.findTitlesSameDirectorAndWriter(pageable);
    }

    @Override
    public Page<TitleBasics> getTitlesTwoActorsByNId(Pageable pageable, String actor1NId, String actor2NId) {
        return titleBasicsRepository.findTitlesWithBothActorsByNConst(pageable, actor1NId, actor2NId);
    }

    @Override
    public Page<TitleBasics> getTitlesTwoActorsByName(Pageable pageable, String actor1Name, String actor2Name) {
        return titleBasicsRepository.findTitlesWithBothActorsByName(pageable, actor1Name, actor2Name);
    }

    @Override
    public List<TitleBasics> getBestTitlesOfYearByGenre(String genre) {
        return titleBasicsRepository.getBestTitlesOfYearByGenre(genre);
    }

}