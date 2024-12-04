package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<Client> findAll();


    Client findById(UUID id);
    Client addClient(Client client);
    void delete(UUID id);
    Client update(Client client, UUID id);
    void buyCar( Client client, Car car);
}
