package ru.sbercources.cinema.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sbercources.cinema.dto.UserDto;
import ru.sbercources.cinema.model.User;

@Component
public class UserMapper extends GenericMapper <User, UserDto>{
    protected UserMapper(ModelMapper modelMapper) {
        super(modelMapper, User.class, UserDto.class);
    }
}
