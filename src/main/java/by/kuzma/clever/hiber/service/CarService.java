package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.dto.CarShowroomRequest;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<CarDto> findAll();


    CarDto findById(UUID id);

    CarDto addCar(CarDto carDto);

    void delete(UUID id);

    CarDto update(CarDto carDto, UUID id);

    void assignCarToShowroom(CarShowroomRequest showroom, UUID id);

}
