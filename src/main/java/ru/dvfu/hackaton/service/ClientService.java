package ru.dvfu.hackaton.service;

import org.springframework.stereotype.Service;
import ru.dvfu.hackaton.model.Client;
import ru.dvfu.hackaton.repository.ClientRepository;

import javax.annotation.Resource;

/**
 * Created by anton on 07.04.17.
 */
@Service
public class ClientService {

    @Resource
    ClientRepository clientRepository;

    public boolean auth(String email, String password) {
        Client client = clientRepository.findClientByEmail(email);
        if (client != null) {
            return client.getPassword().equals(password);
        } else {
            return false;
        }
    }

    public boolean signup(String email, String password, String description, String name, String purse) {
        if ((clientRepository.findClientByEmail(email) == null) && (clientRepository.findClientByName(name) == null)) {
            Client client = new Client(email, password, description, name, purse);
            clientRepository.save(client);
            return true;
        } else {
            return false;
        }
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }
}
