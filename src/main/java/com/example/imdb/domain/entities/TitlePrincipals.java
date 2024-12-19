package com.example.imdb.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "title_principals")
@IdClass(TitlePrincipalsId.class)
public class TitlePrincipals {
    @Id
    @Column(name = "tconst")
    private String tconst;

    @Id
    @Column(name = "ordering")
    private Integer ordering;

    @Id
    @Column(name = "nconst")
    private String nconst;

    @Column(name = "category")
    private String category;

    @Column(name = "job")
    private String job;

    @Column(name = "characters")
    private String characters;

    @ManyToOne
    @JoinColumn(name = "tconst", insertable = false, updatable = false)
    private TitleBasics titleBasics;

    @ManyToOne
    @JoinColumn(name = "nconst", insertable = false, updatable = false)
    private NameBasics nameBasics;
}
