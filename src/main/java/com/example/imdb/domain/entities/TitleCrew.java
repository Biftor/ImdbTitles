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
@Table(name = "title_crew")
public class TitleCrew {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tconst", insertable = false, updatable = false)
    private TitleBasics titleBasics;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "directors", nullable = false)
    private String[] directors;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "writers", nullable = false)
    private String[] writers;
}
