package ru.sbercources.cinema.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sbercources.cinema.dto.DirectorsDto;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.repository.FilmRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorsMapper extends GenericMapper<Directors, DirectorsDto> {
    private final ModelMapper mapper;
    private final FilmRepository filmRepository;

    protected DirectorsMapper(ModelMapper modelMapper, FilmRepository filmRepository) {
        super(modelMapper, Directors.class, DirectorsDto.class);
        this.mapper = modelMapper;
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Directors.class, DirectorsDto.class)
                .addMappings(m -> m.skip(DirectorsDto::setFilmsIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(DirectorsDto.class, Directors.class)
                .addMappings(m -> m.skip(Directors::setFilms))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Directors source, DirectorsDto destination) {
//        destination.setFilmsIds(getIds(source));
    }

    @Override
    void mapSpecificFields(DirectorsDto source, Directors destination) {
        if (!Objects.isNull(source.getFilmsIds())) {
            destination.setFilms(filmRepository.findAllByIdIn(source.getFilmsIds()));
        } else {
            destination.setFilms(null);
        }
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
