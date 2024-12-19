package com.example.imdb.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "title_ratings")
public class TitleRatings {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst;

    @Column(name = "average_rating", nullable = false, precision = 3, scale = 1)
    private BigDecimal averageRating;

    @Column(name = "num_votes", nullable = false)
    private Integer numVotes;

    @OneToOne(optional = false)
    @JoinColumn(name = "tconst", insertable = false, updatable = false)
    private TitleBasics titleBasics;
}
