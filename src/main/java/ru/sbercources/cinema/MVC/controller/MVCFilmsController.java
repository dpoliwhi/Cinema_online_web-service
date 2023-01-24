package ru.sbercources.cinema.MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercources.cinema.mapper.FilmMapper;
import ru.sbercources.cinema.service.FilmService;

@Controller
@RequestMapping("/films")
public class MVCFilmsController {

    private final FilmService service;
    private final FilmMapper mapper;

    public MVCFilmsController(FilmService service, FilmMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("films", service.getList().stream().map(mapper::toDto).toList());
        return "films/viewAllFilms";
    }
}
