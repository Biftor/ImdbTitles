package com.example.imdb.repository;

import com.example.imdb.domain.entities.TitleCrew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TitleCrewRepository extends JpaRepository<TitleCrew, String> {

    @Query(
            value = """
            SELECT titleCrew.*
            FROM title_crew titleCrew
            JOIN title_basics titleBasics ON titleBasics.tconst = titleCrew.tconst
            JOIN name_basics nameBasics ON nameBasics.nconst = ANY(string_to_array(titleCrew.directors, ','))
                AND nameBasics.nconst = ANY(string_to_array(titleCrew.writers, ','))
            WHERE nameBasics.death_year IS NULL
        """,
            nativeQuery = true
    )
    Page<TitleCrew> findTitlesSameDirectorAndWriter(Pageable pageable);
}
