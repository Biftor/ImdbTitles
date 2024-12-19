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
            SELECT tb.*
            FROM title_basics tb
            JOIN title_crew tc ON tb.tconst = tc.tconst
            JOIN name_basics nb ON nb.nconst = ANY(string_to_array(tc.directors, ','))
                AND nb.nconst = ANY(string_to_array(tc.writers, ','))
            WHERE nb.death_year IS NULL
        """,
            countQuery = """
            SELECT COUNT(*)
            FROM title_basics tb
            JOIN title_crew tc ON tb.tconst = tc.tconst
            JOIN name_basics nb ON nb.nconst = ANY(string_to_array(tc.directors, ','))
                AND nb.nconst = ANY(string_to_array(tc.writers, ','))
            WHERE nb.death_year IS NULL
        """,
            nativeQuery = true
    )
    Page<TitleBasics> findTitlesSameDirectorAndWriter(Pageable pageable);

    @Query(
            value = """
            SELECT tb.*
            FROM title_basics tb
            WHERE tb.tconst IN (
                SELECT tp1.tconst
                FROM title_principals tp1
                JOIN title_principals tp2 ON tp1.tconst = tp2.tconst
                WHERE tp1.nconst = :actor1 AND tp2.nconst = :actor2
            )
        """,
            nativeQuery = true
    )
    Page<TitleBasics> findTitlesWithBothActorsByNConst(Pageable pageable, @Param("actor1") String actor1, @Param("actor2") String actor2);


    @Query(
            value = """
            SELECT tb.*
            FROM title_basics tb
            WHERE tb.tconst IN (
                SELECT tp1.tconst
                FROM title_principals tp1
                JOIN title_principals tp2 ON tp1.tconst = tp2.tconst
                JOIN name_basics nb1 ON tp1.nconst = nb1.nconst
                JOIN name_basics nb2 ON tp2.nconst = nb2.nconst
                WHERE nb1.primary_name = :actorName1 AND nb2.primary_name = :actorName2
            )
        """,
            nativeQuery = true
    )
    Page<TitleBasics> findTitlesWithBothActorsByName(Pageable pageable, @Param("actorName1") String actorName1, @Param("actorName2") String actorName2);

    @Query(
            value = """

                    SELECT tb.*
            FROM title_basics tb
            JOIN title_ratings tr ON tb.tconst = tr.tconst
            WHERE :genre = ANY(string_to_array(tb.genres, ','))
            AND tb.start_year IS NOT NULL
            AND tr.num_votes > 0
            AND (tb.start_year, tr.num_votes) IN (
                SELECT tb_inner.start_year, MAX(tr_inner.num_votes)
                FROM title_basics tb_inner
                JOIN title_ratings tr_inner ON tb_inner.tconst = tr_inner.tconst
                WHERE :genre = ANY(string_to_array(tb_inner.genres, ','))
                GROUP BY tb_inner.start_year
            )
            ORDER BY tb.start_year DESC
            """,
            countQuery = """
            SELECT COUNT(*)
            FROM (
                SELECT tb_inner.start_year
                FROM title_basics tb_inner
                JOIN title_ratings tr_inner ON tb_inner.tconst = tr_inner.tconst
                WHERE :genre = ANY(string_to_array(tb_inner.genres, ','))
                GROUP BY tb_inner.start_year
            ) AS subquery
            """,
            nativeQuery = true
    )
    Page<TitleBasics> getBestTitlesOfYearByGenrePaged(Pageable pageable, @Param("genre") String genre);

    @Query(value = """
            SELECT tb.*
            FROM title_basics tb
            JOIN title_ratings tr ON tb.tconst = tr.tconst
            WHERE :genre = ANY(string_to_array(tb.genres, ','))
            AND tb.start_year IS NOT NULL
            AND tr.num_votes > 0
            AND (tb.start_year, tr.num_votes) IN (
                SELECT tb_inner.start_year, MAX(tr_inner.num_votes)
                FROM title_basics tb_inner
                JOIN title_ratings tr_inner ON tb_inner.tconst = tr_inner.tconst
                WHERE :genre = ANY(string_to_array(tb_inner.genres, ','))
                GROUP BY tb_inner.start_year
            )
            ORDER BY tb.start_year DESC
            """, nativeQuery = true
    )
    List<TitleBasics> getBestTitlesOfYearByGenre(String genre);
}
