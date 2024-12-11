package by.kuzma.clever.hiber.service.impl;

import by.kuzma.clever.hiber.dto.ClientBuyCar;
import by.kuzma.clever.hiber.dto.ClientDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Client;
import by.kuzma.clever.hiber.exception.NotFoundDataException;
import by.kuzma.clever.hiber.mapper.ClientMapper;
import by.kuzma.clever.hiber.repository.CarRepository;
import by.kuzma.clever.hiber.repository.ClientRepository;
import by.kuzma.clever.hiber.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;

    private final CarRepository carRepository;
    private final ClientMapper mapper;


    @Override
    public List<ClientDto> findAll() {

        return mapper.toDtos(repository.findAll());
    }

    @Override
    public ClientDto findById(UUID id) {

        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public ClientDto addClient(ClientDto client) {
        return mapper.toDto(repository.save(mapper.toEntity(client)));
    }

    @Override
    public void delete(UUID id) {
        if(!repository.existsById(id)) {
            throw new NotFoundDataException(id, Client.class);
        }
        repository.deleteById(id);

    }

    @Override
    public ClientDto update(ClientDto clientDto, UUID id) {
        Client client = mapper.toEntity(clientDto);
        client.setId(id);

        return mapper.toDto(repository.save(client));
    }

    @Override
    public void buyCar(ClientBuyCar buyCar) {
        Client client = mapper.buyCarToEntity(buyCar);

        client = repository.findById(client.getId())
                .orElseThrow(() -> new NotFoundDataException(buyCar.id(), Client.class));
        UUID carID = buyCar.cars().get(0).id();
        Car car = carRepository.findById(carID)
                .orElseThrow(() -> new NotFoundDataException(carID, Car.class));
        client.addCar(car);

    }
}