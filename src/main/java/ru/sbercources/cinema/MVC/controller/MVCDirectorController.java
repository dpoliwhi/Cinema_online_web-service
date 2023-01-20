package ru.sbercources.cinema.MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercources.cinema.dto.DirectorsDto;
import ru.sbercources.cinema.mapper.DirectorsMapper;
import ru.sbercources.cinema.service.DirectorsService;

@Controller
@RequestMapping("/directors")
public class MVCDirectorController {

    private final DirectorsService service;
    private final DirectorsMapper mapper;

    public MVCDirectorController(DirectorsService service, DirectorsMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("directors", service.getList().stream().map(mapper::toDto).toList());
        return "directors/viewAllDirectors";
    }

    @GetMapping("/add")
    public String create() {
        return "directors/addDirector";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("directorsForm")DirectorsDto directorsDto) {
        service.create(mapper.toEntity(directorsDto));
        return "redirect:/directors";
    }
}
