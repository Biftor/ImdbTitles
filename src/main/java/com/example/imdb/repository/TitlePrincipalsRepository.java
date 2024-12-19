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
            SELECT titlePrincipals1.*
            FROM title_principals titlePrincipals1
            JOIN title_principals titlePrincipals2 ON titlePrincipals1.tconst = titlePrincipals2.tconst
            WHERE titlePrincipals1.nconst = :actor1 AND titlePrincipals2.nconst = :actor2
            """,
            nativeQuery = true
    )
    Page<TitlePrincipals> findTitlesWithBothActorsByNConst(Pageable pageable, @Param("actor1") String actor1, @Param("actor2") String actor2);


    @Query(value = """
            SELECT titlePrincipals1.*
            FROM title_principals titlePrincipals1
            JOIN title_principals titlePrincipals2 ON titlePrincipals1.tconst = titlePrincipals2.tconst
            JOIN name_basics nameBasics1 ON titlePrincipals1.nconst = nameBasics1.nconst
            JOIN name_basics nameBasics2 ON titlePrincipals2.nconst = nameBasics2.nconst
            WHERE nameBasics1.primary_name = :actorName1 AND nameBasics2.primary_name = :actorName2
            """,
            nativeQuery = true
    )
    Page<TitlePrincipals> findTitlesWithBothActorsByName(Pageable pageable, @Param("actorName1") String actorName1, @Param("actorName2") String actorName2);

}
