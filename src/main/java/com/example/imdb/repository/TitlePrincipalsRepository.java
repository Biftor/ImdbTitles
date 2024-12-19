package com.example.imdb.repository;

import com.example.imdb.domain.entities.TitlePrincipals;
import com.example.imdb.domain.entities.TitlePrincipalsId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TitlePrincipalsRepository extends JpaRepository<TitlePrincipals, TitlePrincipalsId> {

    @Query(value = """
            SELECT tp1.*
            FROM title_principals tp1
            JOIN title_principals tp2 ON tp1.tconst = tp2.tconst
            WHERE tp1.nconst = :actor1 AND tp2.nconst = :actor2
            """,
            nativeQuery = true
    )
    Page<TitlePrincipals> findTitlesWithBothActorsByNConst(Pageable pageable, @Param("actor1") String actor1, @Param("actor2") String actor2);


    @Query(value = """
            SELECT tp1.*
            FROM title_principals tp1
            JOIN title_principals tp2 ON tp1.tconst = tp2.tconst
            JOIN name_basics nb1 ON tp1.nconst = nb1.nconst
            JOIN name_basics nb2 ON tp2.nconst = nb2.nconst
            WHERE nb1.primary_name = :actorName1 AND nb2.primary_name = :actorName2
            """,
            nativeQuery = true
    )
    Page<TitlePrincipals> findTitlesWithBothActorsByName(Pageable pageable, @Param("actorName1") String actorName1, @Param("actorName2") String actorName2);

}
