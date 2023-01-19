package ru.sbercources.cinema.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sbercources.cinema.dto.FilmDto;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.repository.DirectorsRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDto> {

    private final ModelMapper mapper;
    private final DirectorsRepository directorsRepository;

    protected FilmMapper(ModelMapper mapper, DirectorsRepository directorsRepository) {
        super(mapper, Film.class, FilmDto.class);
        this.mapper = mapper;
        this.directorsRepository = directorsRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Film.class, FilmDto.class)
                .addMappings(m -> m.skip(FilmDto::setDirectorsIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(FilmDto.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Film source, FilmDto destination) {
        destination.setDirectorsIds(getIds(source));
    }

    @Override
    void mapSpecificFields(FilmDto source, Film destination) {
        destination.setDirectors(directorsRepository.findAllByIdIn(source.getDirectorsIds()));
    }

    private Set<Long> getIds(Film director) {
        return Objects.isNull(director) || Objects.isNull(director.getId())
                ? null
                : director.getDirectors()
                .stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
