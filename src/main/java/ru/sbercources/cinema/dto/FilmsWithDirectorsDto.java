package ru.sbercources.cinema.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmsWithDirectorsDto extends FilmDto {
    private Set<DirectorsDto> directors;
}
