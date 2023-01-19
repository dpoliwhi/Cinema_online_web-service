package ru.sbercources.cinema.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.dto.UserDto;
import ru.sbercources.cinema.mapper.UserMapper;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.User;
import ru.sbercources.cinema.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserController extends GenericController<User, UserDto> {

    private final UserService service;

    public UserController(UserService service, UserMapper mapper) {
        super(service, mapper);
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/orderedFilms/{id}")
    @Operation(description = "Получить все фильмы пользователя по его id", method = "Update")
    public List<Film> getOrderedFilms(@PathVariable Long id) {
        return service.getOrderedFilms(id);
    }
}