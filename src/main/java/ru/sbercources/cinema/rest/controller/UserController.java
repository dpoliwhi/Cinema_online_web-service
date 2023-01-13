package ru.sbercources.cinema.rest.controller;

import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.model.User;
import ru.sbercources.cinema.repository.GenericRepository;

@RestController
@RequestMapping("/rest/user")
public class UserController extends GenericController<User> {
    public UserController(GenericRepository<User> repository) {
        super(repository);
    }
}