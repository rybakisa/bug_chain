package ru.dvfu.hackaton.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.hackaton.model.Client;
import ru.dvfu.hackaton.service.ClientService;

import javax.annotation.Resource;

/**
 * Created by anton on 15.03.17.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    ClientService clientService;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Client getClientByEmail(@RequestParam("email") String email) {
        return clientService.getClientByEmail(email);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseEntity<Object> indexAction(@RequestParam("email") String email, @RequestParam("password") String password) {
        if (clientService.auth(email, password)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> signup(@RequestParam("email") String email,
                                         @RequestParam("password") String password,
                                         @RequestParam("name") String name,
                                         @RequestParam("purse") String purse,
                                         @RequestParam("description") String description) {
        if (clientService.signup(email, password, description, name, purse)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

    }
}