package ru.sbercources.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsDto extends GenericDto {
    private String directorsFio;
    private String position;
    private Set<Long> filmsIds;
}
