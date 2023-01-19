package ru.sbercources.cinema.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.dto.FilmDto;
import ru.sbercources.cinema.dto.FilmsWithDirectorsDto;
import ru.sbercources.cinema.mapper.FilmMapper;
import ru.sbercources.cinema.mapper.FilmsWithDirectorsMapper;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Genre;
import ru.sbercources.cinema.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("rest/film")
public class FilmController extends GenericController<Film, FilmDto> {

    public final FilmService service;
    public final FilmsWithDirectorsMapper filmsWithDirectorsMapper;

    public FilmController(FilmService service, FilmMapper mapper, FilmsWithDirectorsMapper filmsWithDirectorsMapper) {
        super(service, mapper);
        this.service = service;
        this.filmsWithDirectorsMapper = filmsWithDirectorsMapper;
    }

    @ResponseBody
    @PutMapping("/addDirectors/{id}")
    @Operation(description = "Добавить режиссеров к фильму по id", method = "AddDirectors")
    public ResponseEntity<?> addDirectors(@PathVariable Long id, @RequestBody List<Directors> directorsToAdd) {
        try {
            return ResponseEntity.ok().body(service.addDirector(id, directorsToAdd));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such film with id " + id);
        }
    }

    @GetMapping("/search")
    public List<Film> search(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "genre", required = false) Genre genre
    ) {
        return service.search(title, country, genre);
    }

    @GetMapping("/films-director")
    public List<FilmsWithDirectorsDto> getDirectorsWithFilms() {
        return service.getList().stream().map(filmsWithDirectorsMapper::toDto).toList();
    }
}
