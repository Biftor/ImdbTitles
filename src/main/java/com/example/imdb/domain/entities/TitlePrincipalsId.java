package com.example.imdb.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitlePrincipalsId implements Serializable {

    private String tconst;
    private Integer ordering;
    private String nconst;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitlePrincipalsId that = (TitlePrincipalsId) o;
        return Objects.equals(tconst, that.tconst) &&
                Objects.equals(ordering, that.ordering) &&
                Objects.equals(nconst, that.nconst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tconst, ordering, nconst);
    }
}
