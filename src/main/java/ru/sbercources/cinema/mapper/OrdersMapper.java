package ru.sbercources.cinema.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sbercources.cinema.dto.OrdersDto;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.repository.FilmRepository;
import ru.sbercources.cinema.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrdersMapper extends GenericMapper<Orders, OrdersDto> {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    protected OrdersMapper(ModelMapper modelMapper, UserRepository userRepository, FilmRepository filmRepository) {
        super(modelMapper, Orders.class, OrdersDto.class);
        this.mapper = modelMapper;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Orders.class, OrdersDto.class)
                .addMappings(m -> m.skip(OrdersDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrdersDto::setFilmId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(OrdersDto.class, Orders.class)
                .addMappings(m -> m.skip(Orders::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Orders::setFilm)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Orders source, OrdersDto destination) {
        destination.setFilmId(source.getFilm().getId());
        destination.setUserId(source.getUser().getId());
    }

    @Override
    void mapSpecificFields(OrdersDto source, Orders destination) {
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow());
        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow());
    }
}
