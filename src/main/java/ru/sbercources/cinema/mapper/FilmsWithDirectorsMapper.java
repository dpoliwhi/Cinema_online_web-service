package ru.sbercources.cinema.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sbercources.cinema.dto.FilmsWithDirectorsDto;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.repository.DirectorsRepository;
import ru.sbercources.cinema.repository.FilmRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmsWithDirectorsMapper extends GenericMapper<Film, FilmsWithDirectorsDto> {

    private final ModelMapper mapper;
    private final DirectorsRepository directorsRepository;

    protected FilmsWithDirectorsMapper(ModelMapper modelMapper, DirectorsRepository directorsRepository) {
        super(modelMapper, Film.class, FilmsWithDirectorsDto.class);
        this.mapper = modelMapper;
        this.directorsRepository = directorsRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Film.class, FilmsWithDirectorsDto.class)
                .addMappings(m -> m.skip(FilmsWithDirectorsDto::setDirectorsIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(FilmsWithDirectorsDto.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Film source, FilmsWithDirectorsDto destination) {
        destination.setDirectorsIds(getIds(source));
    }

    @Override
    void mapSpecificFields(FilmsWithDirectorsDto source, Film destination) {
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
