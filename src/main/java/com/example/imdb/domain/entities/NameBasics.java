package com.example.imdb.domain.entities;

import com.example.imdb.domain.converter.StringArrayConverter;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "name_basics")
public class NameBasics {

    @Id
    @Column(name = "nconst", nullable = false)
    private String nconst;

    @Column(name = "primary_name", nullable = false)
    private String primaryName;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "primary_profession")
    private String[] primaryProfession;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "known_for_titles")
    private String[] knownForTitles;

    //It reduces the performance, we don't need it for now
    /*@OneToMany
    @JoinTable(name = "title_principals", joinColumns = @JoinColumn(name = "nconst"), inverseJoinColumns = @JoinColumn(name = "tconst"))
    private List<TitleBasics> movies;*/
}


