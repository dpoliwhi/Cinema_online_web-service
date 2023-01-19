package ru.sbercources.cinema.dto;

import lombok.*;
import ru.sbercources.cinema.model.Genre;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto extends GenericDto {

    private String title;
    private Date premierYear;
    private String country;
    private Genre genre;
    private Set<Long> directorsIds;
}
