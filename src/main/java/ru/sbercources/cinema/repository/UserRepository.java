package ru.sbercources.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.sbercources.cinema.model.User;

@Repository
public interface UserRepository extends GenericRepository<User> {
}
