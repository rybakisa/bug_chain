package ru.dvfu.hackaton.service;

import org.springframework.stereotype.Service;
import ru.dvfu.hackaton.model.Task;
import ru.dvfu.hackaton.repository.TaskRepository;

import javax.annotation.Resource;

/**
 * Created by anton on 07.04.17.
 */
@Service
public class TaskService {
    @Resource
    private TaskRepository taskRepository;

    public void save(Task task) {
        taskRepository.save(task);
    }
}
