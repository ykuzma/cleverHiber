package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {

    Client save(Client client);
    List<Client> findAll();
    Client findById(UUID id);
    void deleteById(UUID id);
    Client update(Client client);
}
