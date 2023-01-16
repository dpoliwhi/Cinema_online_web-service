package ru.sbercources.cinema.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Genre;
import ru.sbercources.cinema.service.FilmService;
import ru.sbercources.cinema.service.GenericService;

import java.util.List;

@RestController
@RequestMapping("rest/film")
public class FilmController extends GenericController<Film> {

    public final FilmService service;
    public FilmController(FilmService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/search")
    public List<Film> search(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "genre", required = false) Genre genre
    ) {
        return service.search(title, country, genre);
    }


}
