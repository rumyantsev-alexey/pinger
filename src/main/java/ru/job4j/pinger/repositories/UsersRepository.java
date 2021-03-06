package ru.job4j.pinger.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.pinger.models.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
