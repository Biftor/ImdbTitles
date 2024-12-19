package com.example.imdb.domain.entities;

import com.example.imdb.domain.converter.StringArrayConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "title_basics")
public class TitleBasics {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst;

    @Column(name = "title_type", nullable = false)
    private String titleType;

    @Column(name = "primary_title", nullable = false)
    private String primaryTitle;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "is_adult", nullable = false)
    private Boolean isAdult;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "runtime_minutes")
    private Integer runtimeMinutes;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "genres")
    private String[] genres;

    @OneToMany
    @JoinTable(name = "title_principals", joinColumns = @JoinColumn(name = "tconst"),
            inverseJoinColumns = @JoinColumn(name = "nconst"))
    @ToString.Exclude
    private List<NameBasics> actors;
}
