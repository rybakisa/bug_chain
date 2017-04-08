package ru.dvfu.hackaton;

//import de.codecentric.boot.admin.config.EnableAdminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import ru.dvfu.agregator.config.SpringConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
//@EnableAdminServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
