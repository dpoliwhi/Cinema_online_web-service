package ru.sbercources.cinema.service;

import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.repository.GenericRepository;

@Service
public class DirectorsService extends GenericService<Directors> {
    protected DirectorsService(GenericRepository<Directors> repository) {
        super(repository);
    }
}
