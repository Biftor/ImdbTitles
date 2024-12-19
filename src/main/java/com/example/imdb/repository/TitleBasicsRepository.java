package com.example.imdb.repository;

import com.example.imdb.domain.entities.TitleBasics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleBasicsRepository extends JpaRepository<TitleBasics, String> {

    @Query(
            value = """
            SELECT titleBasics.*
            FROM title_basics titleBasics
            JOIN title_crew titleCrew ON titleBasics.tconst = titleCrew.tconst
            JOIN name_basics nameBasics ON nameBasics.nconst = ANY(string_to_array(titleCrew.directors, ','))
                AND nameBasics.nconst = ANY(string_to_array(titleCrew.writers, ','))
            WHERE nameBasics.death_year IS NULL
        """,
            nativeQuery = true
    )
    Page<TitleBasics> findTitlesSameDirectorAndWriter(Pageable pageable);

    @Query(
            value = """
            SELECT titleBasics.*
            FROM title_basics titleBasics
            WHERE titleBasics.tconst IN (
                SELECT titlePrincipals1.tconst
                FROM title_principals titlePrincipals1
                JOIN title_principals titlePrincipals2 ON titlePrincipals1.tconst = titlePrincipals2.tconst
                WHERE titlePrincipals1.nconst = :actor1 AND titlePrincipals2.nconst = :actor2
            )
        """,
            nativeQuery = true
    )
    Page<TitleBasics> findTitlesWithBothActorsByNConst(Pageable pageable, @Param("actor1") String actor1, @Param("actor2") String actor2);


    @Query(
            value = """
            SELECT titleBasics.*
            FROM title_basics titleBasics
            WHERE titleBasics.tconst IN (
                SELECT titlePrincipals1.tconst
                FROM title_principals titlePrincipals1
                JOIN title_principals titlePrincipals2 ON titlePrincipals1.tconst = titlePrincipals2.tconst
                JOIN name_basics nameBasics1 ON titlePrincipals1.nconst = nameBasics1.nconst
                JOIN name_basics nameBasics2 ON titlePrincipals2.nconst = nameBasics2.nconst
                WHERE nameBasics1.primary_name = :actorName1 AND nameBasics2.primary_name = :actorName2
            )
        """,
            nativeQuery = true
    )
    Page<TitleBasics> findTitlesWithBothActorsByName(Pageable pageable, @Param("actorName1") String actorName1, @Param("actorName2") String actorName2);

    @Query(
            value = """
            SELECT titleBasics.*
            FROM title_basics titleBasics
            JOIN title_ratings titleRatings ON titleBasics.tconst = titleRatings.tconst
            WHERE :genre = ANY(string_to_array(titleBasics.genres, ','))
            AND titleBasics.start_year IS NOT NULL
            AND titleRatings.num_votes > 0
            AND (titleBasics.start_year, titleRatings.num_votes) IN (
                SELECT inner_titleBasics.start_year, MAX(inner_titleRatings.num_votes)
                FROM title_basics inner_titleBasics
                JOIN title_ratings inner_titleRatings ON inner_titleBasics.tconst = inner_titleRatings.tconst
                WHERE :genre = ANY(string_to_array(inner_titleBasics.genres, ','))
                GROUP BY inner_titleBasics.start_year
            )
            ORDER BY titleBasics.start_year DESC
            """,
            nativeQuery = true
    )
    Page<TitleBasics> getBestTitlesOfYearByGenrePaged(Pageable pageable, @Param("genre") String genre);

    @Query(value = """
            SELECT titleBasics.*
            FROM title_basics titleBasics
            JOIN title_ratings titleRatings ON titleBasics.tconst = titleRatings.tconst
            WHERE :genre = ANY(string_to_array(titleBasics.genres, ','))
            AND titleBasics.start_year IS NOT NULL
            AND titleRatings.num_votes > 0
            AND (titleBasics.start_year, titleRatings.num_votes) IN (
                SELECT inner_titleBasics.start_year, MAX(inner_titleRatings.num_votes)
                FROM title_basics inner_titleBasics
                JOIN title_ratings inner_titleRatings ON inner_titleBasics.tconst = inner_titleRatings.tconst
                WHERE :genre = ANY(string_to_array(inner_titleBasics.genres, ','))
                GROUP BY inner_titleBasics.start_year
            )
            ORDER BY titleBasics.start_year DESC
            """, nativeQuery = true
    )
    List<TitleBasics> getBestTitlesOfYearByGenre(String genre);
}
