package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Car;

import java.util.List;
import java.util.UUID;

public interface CarRepository {
    Car save(Car car);
    Car findById(UUID id);
    List<Car> findAll();
    void deleteByID(UUID id);


}
