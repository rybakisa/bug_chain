package ru.dvfu.hackaton.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.hackaton.model.Client;

/**
 * Created by anton on 07.04.17.
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

    public Client findClientByEmail(String email);

    public Client findClientByName(String name);
}
