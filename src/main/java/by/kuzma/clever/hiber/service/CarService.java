package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<Car> findAll();

    List<Car> findAllWithPagination(int pageNumber, int pageSize);

    Car findById(UUID id);

    Car addCar(Car car);

    void delete(UUID id);

    Car update(Car car, UUID id);

    List<Car> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice);

    List<Car> findCarsWithSort(boolean isASC);
    void assignCarToShowroom(Car car, CarShowroom showroom);

}
