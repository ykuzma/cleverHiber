package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.entity.Car;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<Car> findAll();
    Car findById(UUID id);
    Car addCar(Car car);
    void delete(UUID id);
    Car update(Car car, UUID id);

}