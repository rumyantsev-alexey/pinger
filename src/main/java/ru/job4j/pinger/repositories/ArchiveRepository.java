package ru.job4j.pinger.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.pinger.models.Task;
import ru.job4j.pinger.models.User;

import java.util.List;

@Repository
public interface ArchiveRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByUser(User user);
}
