package ru.sbercources.cinema.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sbercources.cinema.dto.DirectorsWithFilmsDto;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.repository.FilmRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorsWithFilmsMapper extends GenericMapper<Directors, DirectorsWithFilmsDto> {

    private final ModelMapper mapper;
    private final FilmRepository filmRepository;

    protected DirectorsWithFilmsMapper(ModelMapper mapper, FilmRepository filmRepository) {
        super(mapper, Directors.class, DirectorsWithFilmsDto.class);
        this.mapper = mapper;
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Directors.class, DirectorsWithFilmsDto.class)
                .addMappings(m -> m.skip(DirectorsWithFilmsDto::setFilmsIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(DirectorsWithFilmsDto.class, Directors.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Directors source, DirectorsWithFilmsDto destination) {
        destination.setFilmsIds(getIds(source));
    }

    @Override
    void mapSpecificFields(DirectorsWithFilmsDto source, Directors destination) {
        destination.setFilms(filmRepository.findAllByIdIn(source.getFilmsIds()));
    }

    private Set<Long> getIds(Directors director) {
        return Objects.isNull(director) || Objects.isNull(director.getId())
                ? null
                : director.getFilms()
                .stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
