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
@Table(name = "title_akas")
@IdClass(TitleAkasId.class)
public class TitleAkas {

    @Id
    @Column(name = "title_id", nullable = false)
    private String titleId;

    @Id
    @Column(name = "ordering", nullable = false)
    private Integer ordering;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "region")
    private String region;

    @Column(name = "language")
    private String language;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "types")
    private String[] types;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "attributes")
    private String[] attributes;

    @Column(name = "is_original_title")
    private Boolean isOriginalTitle;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "title_id", insertable = false, updatable = false)
    private TitleBasics titleBasics;
}
