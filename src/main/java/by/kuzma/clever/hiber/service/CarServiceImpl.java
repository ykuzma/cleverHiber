package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.repository.CarRepository;

import java.util.List;
import java.util.UUID;


public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(UUID id) {
        return null;
    }

    @Override
    public Car addCar(Car car) {


        return repository.save(car);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Car update(Car car, UUID id) {
        return null;
    }
}
