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
            SELECT tc.*
            FROM title_crew tc
            JOIN title_basics tb ON tb.tconst = tc.tconst
            JOIN name_basics nb ON nb.nconst = ANY(string_to_array(tc.directors, ','))
                AND nb.nconst = ANY(string_to_array(tc.writers, ','))
            WHERE nb.death_year IS NULL
        """,
            countQuery = """
            SELECT COUNT(*)
            FROM title_crew tc
            JOIN title_basics tb ON tb.tconst = tc.tconst
            JOIN name_basics nb ON nb.nconst = ANY(string_to_array(tc.directors, ','))
                AND nb.nconst = ANY(string_to_array(tc.writers, ','))
            WHERE nb.death_year IS NULL
        """,
            nativeQuery = true
    )
    Page<TitleCrew> findTitlesSameDirectorAndWriter(Pageable pageable);
}
