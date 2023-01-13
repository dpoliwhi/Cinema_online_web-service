package ru.sbercources.cinema.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.repository.GenericRepository;

@RestController
@RequestMapping("rest/film")
public class FilmController extends GenericController <Film>{
    public FilmController(GenericRepository<Film> repository) {
        super(repository);
    }
}
