package ru.dvfu.hackaton.config;

import org.springframework.context.annotation.Bean;
import ru.dvfu.hackaton.service.ClientService;
import ru.dvfu.hackaton.service.TaskService;

/**
 * Created by anton on 08.04.17.
 */
public class SpringConfig {
    @Bean
    public ClientService getClientService() {
        return new ClientService();
    }

    public TaskService getTaskService() {
        return new TaskService();
    }


}
