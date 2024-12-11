package by.kuzma.clever.hiber.service.impl;

import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.dto.CarShowroomRequest;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.exception.NotFoundDataException;
import by.kuzma.clever.hiber.mapper.CarMapper;
import by.kuzma.clever.hiber.repository.CarRepository;
import by.kuzma.clever.hiber.repository.CarShowroomRepository;
import by.kuzma.clever.hiber.repository.CategoryRepository;
import by.kuzma.clever.hiber.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CategoryRepository categoryRepository;
    private final CarShowroomRepository showroomRepository;

    private final CarMapper carMapper;


    @Override
    public List<CarDto> findAll() {

        return carMapper.toCarsDto(repository.findAll());

    }


    @Override
    public CarDto findById(UUID id) {

        return carMapper.toCarDto(repository.findById(id).orElseThrow(() -> new NotFoundDataException(id, Car.class)));
    }

    @Override
    public CarDto addCar(CarDto carDto) {

        try {
            Car car = carMapper.toCar(carDto);
            car.setCategory(categoryRepository.getReferenceById(car.getCategory().getId()));

            return carMapper.toCarDto(repository.save(car));
        } catch (EntityNotFoundException e) {
            throw new NotFoundDataException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundDataException(id, Car.class);
        }
        repository.deleteById(id);
    }

    @Override
    public CarDto update(CarDto carDto, UUID id) {

        Car car = carMapper.toCar(carDto);
        car.setId(id);

        return carMapper.toCarDto(repository.save(car));
    }


    public void assignCarToShowroom(CarShowroomRequest showroom, UUID id) {
        try {
            Car car = repository.findById(id).orElseThrow(() -> new NotFoundDataException(id, Car.class));
            car.setCarShowroom(showroomRepository.getReferenceById(showroom.id()));
        } catch (EntityNotFoundException e) {
            throw  new NotFoundDataException(e.getMessage(), e);
        }


    }


}
