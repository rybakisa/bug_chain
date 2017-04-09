package ru.dvfu.hackaton.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.hackaton.model.Task;

/**
 * Created by anton on 07.04.17.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, String> {
    Task findTaskById(int id);
}
