package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.dto.ClientBuyCar;
import by.kuzma.clever.hiber.dto.ClientDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<ClientDto> findAll();


    ClientDto findById(UUID id);
    ClientDto addClient(ClientDto client);
    void delete(UUID id);
    ClientDto update(ClientDto client, UUID id);
    void buyCar(ClientBuyCar buyCar);


}
