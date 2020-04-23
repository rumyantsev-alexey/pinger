package ru.job4j.pinger.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.pinger.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
