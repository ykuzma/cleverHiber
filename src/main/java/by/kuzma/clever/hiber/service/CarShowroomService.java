package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;

import java.util.List;
import java.util.UUID;

public interface CarShowroomService {

    List<CarShowroom> findAll();

    List<CarShowroom> getShowroomWithAllCars();
    CarShowroom findById(UUID id);
    CarShowroom addShowroom(CarShowroom carShowroom);
    void delete(UUID id);
    CarShowroom update(CarShowroom carShowroom, UUID id);
    void assignCarToShowroom(Car car, CarShowroom showroom);
}
