package ru.sbercources.cinema.mapper;

import ru.sbercources.cinema.dto.GenericDto;
import ru.sbercources.cinema.model.GenericModel;

public interface Mapper<E extends GenericModel, D extends GenericDto> {
    E toEntity (D dto);

    D toDto(E entity);
}
