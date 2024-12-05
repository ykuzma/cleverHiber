package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.CarShowroom;

import java.util.List;
import java.util.UUID;

public interface CarShowroomRepository {

    CarShowroom save(CarShowroom carShowroom);
    List<CarShowroom> findAll();
    CarShowroom findById(UUID id);
    void deleteById(UUID id);
    CarShowroom update(CarShowroom carShowroom);

    List<CarShowroom> getShowroomWithAllCars();
}
