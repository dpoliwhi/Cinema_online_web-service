package ru.sbercources.cinema.dto;

import lombok.*;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.User;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto extends GenericDto {
    private Long userId;
    private Long filmId;
    private LocalDate rentDate;
    private LocalDate rentPeriod;
    private boolean purchase;
}
