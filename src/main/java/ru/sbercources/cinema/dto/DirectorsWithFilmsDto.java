package ru.sbercources.cinema.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsWithFilmsDto extends DirectorsDto {
    private Set<FilmDto> films;
}
