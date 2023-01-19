package ru.sbercources.cinema.dto;

import lombok.*;
import ru.sbercources.cinema.model.Film;

import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsDto extends GenericDto {
    private String directorsFio;
    private int position;
    private Set<Long> filmsIds;
}
