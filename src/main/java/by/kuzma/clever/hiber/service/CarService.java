package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<CarDto> findAll();

    List<CarDto> findAllWithPagination(int pageNumber, int pageSize);

    CarDto findById(UUID id);

    CarDto addCar(CarDto carDto);

    void delete(UUID id);

    CarDto update(CarDto carDto, UUID id);

    List<CarDto> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice);

    List<CarDto> findCarsWithSort(boolean isASC);
    void assignCarToShowroom(CarDto carDto, CarShowroom showroom);

}
