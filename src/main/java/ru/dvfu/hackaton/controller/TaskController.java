package ru.dvfu.hackaton.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.hackaton.model.Client;
import ru.dvfu.hackaton.model.Task;
import ru.dvfu.hackaton.service.ClientService;
import ru.dvfu.hackaton.service.TaskService;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * Created by anton on 08.04.17.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api")
public class TaskController {
    @Resource
    TaskService taskService;
    @Resource
    ClientService clientService;

//    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
//    public Set<Task> getTasksByEmail(@RequestParam("email") String email) {
//        Client client = clientService.getClientByEmail(email);
//        return client.getTasks();
//    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public void addTask(@RequestParam("email") String email,
                        @RequestParam("name") String name,
                        @RequestParam("count") int count,
                        @RequestParam("ip") String ip,
                        @RequestParam("port") int port,
                        @RequestParam("description") String description,
                        @RequestParam("price") float price,
                        @RequestParam("flag") String flag,
                        @RequestParam("yearFrom") int yearFrom,
                        @RequestParam("monthFrom") int monthFrom,
                        @RequestParam("dayFrom") int dayFrom,
                        @RequestParam("yearTo") int yearTo,
                        @RequestParam("monthTo") int monthTo,
                        @RequestParam("dayTo") int dayTo)

    {
        LocalDate from = LocalDate.of(yearFrom, monthFrom, dayFrom);
        LocalDate to = LocalDate.of(yearTo, monthTo, dayTo);
        Task task = new Task(name, count, ip, port, description, price, flag, from, to);
        taskService.save(task);

        Client client = clientService.getClientByEmail(email);
        client.getTasks().add(task);
        clientService.save(client);
    }
}
