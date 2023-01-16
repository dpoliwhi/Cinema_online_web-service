package ru.sbercources.cinema.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.repository.GenericRepository;
import ru.sbercources.cinema.service.GenericService;

@RestController
@RequestMapping("/rest/directors")
public class DirectorsController extends GenericController<Directors> {
    public DirectorsController(GenericService<Directors> service) {
        super(service);
    }
}
