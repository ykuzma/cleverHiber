package by.kuzma.clever.hiber.service.impl;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.entity.Category;
import by.kuzma.clever.hiber.exception.NotFoundDataException;
import by.kuzma.clever.hiber.mapper.CarMapper;
import by.kuzma.clever.hiber.repository.CarDao;
import by.kuzma.clever.hiber.repository.CarRepository;
import by.kuzma.clever.hiber.repository.CategoryRepository;
import by.kuzma.clever.hiber.service.CarService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final EntityManager entityManager;
    private final CarDao dao;
    private final CarRepository repository;
    private final CategoryRepository categoryRepository;

    private final CarMapper carMapper;


    @Override
    public List<CarDto> findAll() {

        return carMapper.toCarsDto(repository.findAll());

    }

    @Override
    public List<CarDto> findAllWithPagination(int pageNumber, int pageSize) {
        EntityTransaction transaction = null;

        List<CarDto> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = carMapper.toCarsDto(dao.findAllWithPagination(pageNumber * pageSize, pageSize));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }

        return cars;
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
        if(!repository.existsById(id)) {
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

    @Override
    public List<CarDto> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice) {
        EntityTransaction transaction = null;

        List<CarDto> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = carMapper.toCarsDto(dao.findCarsByFilters(brand, category, year, minPrice, maxPrice));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }

        return cars;

    }


    @Override
    public List<CarDto> findCarsWithSort(boolean isASC) {
        EntityTransaction transaction = null;

        List<CarDto> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = carMapper.toCarsDto(dao.findCarWithSort(isASC));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }


        return cars;
    }

    public void assignCarToShowroom(CarDto carDto, CarShowroom showroom) {
        EntityTransaction transaction = null;

        try {
            transaction = HibernateUtil.openTransaction();
            Car car = carMapper.toCar(carDto);
            car.setCarShowroom(entityManager.getReference(CarShowroom.class, showroom));
            car.setId(car.getId());
            dao.update(car);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }

    }


}