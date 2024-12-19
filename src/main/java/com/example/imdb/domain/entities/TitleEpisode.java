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
@Table(name = "title_episode")
public class TitleEpisode {
    @Id
    @Column(name = "tconst")
    private String tconst;

    @Column(name = "parent_tconst")
    private String parentTconst;

    @Column(name = "season_number")
    private Integer seasonNumber;

    @Column(name = "episode_number")
    private Integer episodeNumber;

    @OneToOne
    @JoinColumn(name = "tconst", insertable = false, updatable = false)
    private TitleBasics titleBasics;

    @ManyToOne
    @JoinColumn(name = "parent_tconst", insertable = false, updatable = false)
    private TitleBasics parentTitleBasics;
}

