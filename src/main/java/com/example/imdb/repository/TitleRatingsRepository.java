package com.example.imdb.repository;

import com.example.imdb.domain.entities.TitleRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRatingsRepository extends JpaRepository<TitleRatings, String> {

    @Query(value = """
            SELECT *
            FROM (
                SELECT titleRatings.*,
                       ROW_NUMBER() OVER (PARTITION BY titleBasics.start_year ORDER BY titleRatings.num_votes DESC, titleRatings.average_rating DESC, titleBasics.start_year DESC) AS row_num
                FROM title_ratings titleRatings
                JOIN title_basics titleBasics ON titleRatings.tconst = titleBasics.tconst
                WHERE LOWER(:genre) = ANY(string_to_array(LOWER(titleBasics.genres), ','))
                AND titleBasics.start_year IS NOT NULL
                AND titleRatings.num_votes > 0
            ) AS ranked_titles
            WHERE row_num = 1
    """, nativeQuery = true
    )
    List<TitleRatings> getBestRatingsOfYearByGenre(@Param("genre") String genre);

    @Query(value = """
        SELECT titleRatings.*
        FROM title_ratings titleRatings
        JOIN title_basics titleBasics ON titleRatings.tconst = titleBasics.tconst
        WHERE LOWER(:genre) = ANY(string_to_array(LOWER(innertitleBasics.genres), ','))
        AND titleBasics.start_year IS NOT NULL
        AND titleRatings.num_votes > 0
        AND (titleBasics.start_year, titleRatings.num_votes) IN (
            SELECT innertitleBasics.start_year, MAX(innertitleRatings.num_votes)
            FROM title_basics innertitleBasics
            JOIN title_ratings innertitleRatings ON innertitleBasics.tconst = innertitleRatings.tconst
            WHERE LOWER(:genre) = ANY(string_to_array(LOWER(innertitleBasics.genres), ','))
            GROUP BY innertitleBasics.start_year
        )
        ORDER BY titleBasics.start_year DESC
    """, nativeQuery = true
    )
    List<TitleRatings> getBestRatingsOfYearByGenre2(@Param("genre") String genre);
}
