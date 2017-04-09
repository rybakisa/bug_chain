package ru.dvfu.hackaton.service;

import org.springframework.stereotype.Service;
import ru.dvfu.hackaton.model.Task;
import ru.dvfu.hackaton.repository.TaskRepository;

import javax.annotation.Resource;
import java.util.List;

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

    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task getTaskById(int id) {
        return taskRepository.findTaskById(id);
    }
}
