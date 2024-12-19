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
public class TitleAkasId implements Serializable {

    private String titleId;
    private Integer ordering;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleAkasId that = (TitleAkasId) o;
        return Objects.equals(titleId, that.titleId) &&
                Objects.equals(ordering, that.ordering);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleId, ordering);
    }
}
