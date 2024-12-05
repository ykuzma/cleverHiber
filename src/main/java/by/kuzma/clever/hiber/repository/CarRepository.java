package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Car;

import java.util.List;
import java.util.UUID;

public interface CarRepository {
    Car save(Car car);
    Car update(Car car);
    Car findById(UUID id);
    List<Car> findAll();
    void deleteById(UUID id);


    List<Car> findAllWithPagination(int pageNumber, int pageSize);

    List<Car> findCarWithSort(boolean isASC);

    List<Car> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice);
}
