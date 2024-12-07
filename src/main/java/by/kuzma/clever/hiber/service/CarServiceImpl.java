package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.mapper.CarMapper;
import by.kuzma.clever.hiber.repository.CarRepository;
import by.kuzma.clever.hiber.repository.CarRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();
    private final CarRepository repository = new CarRepositoryImpl();
    private final CarMapper carMapper;


    @Override
    public List<CarDto> findAll() {
        Transaction transaction = null;

        List<CarDto> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = carMapper.toCarsDto(repository.findAll());
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
    public List<CarDto> findAllWithPagination(int pageNumber, int pageSize) {
        Transaction transaction = null;

        List<CarDto> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = carMapper.toCarsDto(repository.findAllWithPagination(pageNumber * pageSize, pageSize));
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
        Transaction transaction = null;
        Car car;
        try {
            transaction = HibernateUtil.openTransaction();
            car = repository.findById(id);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return carMapper.toCarDto(car);
    }

    @Override
    public CarDto addCar(CarDto carDto) {
        CarDto carPersist;
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.openTransaction();
            Car car = carMapper.toCar(carDto);
            car.setCategory(sessionFactory.getCurrentSession().getReference(car.getCategory()));
            carPersist = carMapper.toCarDto(repository.save(car));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return carPersist;
    }

    @Override
    public void delete(UUID id) {
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.openTransaction();
            repository.deleteById(id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public CarDto update(CarDto carDto, UUID id) {
        Transaction transaction = null;
        CarDto carUpdated;
        try {
            transaction = HibernateUtil.openTransaction();
            Car car = carMapper.toCar(carDto);
            car.setId(id);
            carUpdated = carMapper.toCarDto(repository.update(car));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return carUpdated;
    }

    @Override
    public List<CarDto> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice) {
        Transaction transaction = null;

        List<CarDto> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = carMapper.toCarsDto(repository.findCarsByFilters(brand, category, year, minPrice, maxPrice));
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
        Transaction transaction = null;

        List<CarDto> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = carMapper.toCarsDto(repository.findCarWithSort(isASC));
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
        Transaction transaction = null;

        try {
            transaction = HibernateUtil.openTransaction();
            Car car = carMapper.toCar(carDto);
            car.setCarShowroom(sessionFactory.getCurrentSession().getReference(showroom));
            car.setId(car.getId());
            repository.update(car);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }

    }


}
