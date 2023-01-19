package ru.sbercources.cinema.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.cinema.dto.DirectorsDto;
import ru.sbercources.cinema.dto.DirectorsWithFilmsDto;
import ru.sbercources.cinema.mapper.DirectorsMapper;
import ru.sbercources.cinema.mapper.DirectorsWithFilmsMapper;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.service.DirectorsService;

import java.util.List;

@RestController
@RequestMapping("/rest/directors")
public class DirectorsController extends GenericController<Directors, DirectorsDto> {

    private final DirectorsService service;
    private final DirectorsWithFilmsMapper directorsWithFilmsMapper;

    public DirectorsController(DirectorsService service, DirectorsMapper mapper, DirectorsWithFilmsMapper directorsWithFilmsMapper) {
        super(service, mapper);
        this.service = service;
        this.directorsWithFilmsMapper = directorsWithFilmsMapper;
    }

    @GetMapping("/director-films")
    public List<DirectorsWithFilmsDto> getDirectorsWithFilms() {
        return service.getList().stream().map(directorsWithFilmsMapper::toDto).toList();
    }
}
