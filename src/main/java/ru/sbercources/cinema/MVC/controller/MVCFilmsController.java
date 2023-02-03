package ru.sbercources.cinema.MVC.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.dto.FilmDto;
import ru.sbercources.cinema.dto.FilmsWithDirectorsDto;
import ru.sbercources.cinema.mapper.DirectorsMapper;
import ru.sbercources.cinema.mapper.FilmMapper;
import ru.sbercources.cinema.mapper.FilmsWithDirectorsMapper;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.service.DirectorsService;
import ru.sbercources.cinema.service.FilmService;

import java.util.List;

@Controller
@RequestMapping("/films")
public class MVCFilmsController {

    private final DirectorsService directorsService;
    private final DirectorsMapper directorsMapper;
    private final FilmService service;
    private final FilmMapper mapper;
    private final FilmsWithDirectorsMapper filmsWithDirectorsMapper;

    public MVCFilmsController(DirectorsService directorsService, DirectorsMapper directorsMapper, FilmService service, FilmMapper mapper, FilmsWithDirectorsMapper filmsWithDirectorsMapper) {
        this.directorsService = directorsService;
        this.directorsMapper = directorsMapper;
        this.service = service;
        this.mapper = mapper;
        this.filmsWithDirectorsMapper = filmsWithDirectorsMapper;
    }

    @GetMapping("")
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        Page<Film> onePage = service.listAllPaginated(pageRequest);
        List<FilmsWithDirectorsDto> filmDtos = onePage
                .stream()
                .map(filmsWithDirectorsMapper::toDto)
                .toList();
        model.addAttribute("films", new PageImpl<>(filmDtos, pageRequest, onePage.getTotalElements()));
        return "films/viewAllFilms";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmsForm") FilmDto filmDto) {
        service.create(mapper.toEntity(filmDto));
        return "redirect:/films";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/films";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("film", mapper.toDto(service.getOneById(id)));
        return "films/updateFilm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmsForm") FilmDto filmDto) {
        service.update(mapper.toEntity(filmDto));
        return "redirect:/films";
    }

    @GetMapping("/addDirector/{id}")
    public String addDirector(@PathVariable Long id, Model model) {
        model.addAttribute("film", mapper.toDto(service.getOneById(id)));
        model.addAttribute("directors", directorsService.getList().stream().map(directorsMapper::toDto).toList());
        return "films/viewDirectorsToAdd";
    }

    @PostMapping("/addDirector/{idFilm}/{idDir}")
    public String addDirector(@PathVariable String idFilm, @PathVariable String idDir) {
        Directors directorToAdd = directorsService.getOneById(Long.valueOf(idDir));
        service.addDirector(Long.valueOf(idFilm), List.of(directorToAdd));
        return "redirect:/films";
    }
}
