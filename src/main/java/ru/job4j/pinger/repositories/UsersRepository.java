package ru.job4j.pinger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.pinger.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
