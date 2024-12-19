package com.example.imdb.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageRequestDTO {
    private int page = 1;
    private int size = 20;
}
