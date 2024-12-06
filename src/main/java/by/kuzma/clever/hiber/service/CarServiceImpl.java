package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.repository.CarRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;


public class CarServiceImpl implements CarService {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();
    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Car> findAll() {
        Transaction transaction = null;

        List<Car> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = repository.findAll();
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
    public List<Car> findAllWithPagination(int pageNumber, int pageSize) {
        Transaction transaction = null;

        List<Car> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = repository.findAllWithPagination(pageNumber * pageSize, pageSize);
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
    public Car findById(UUID id) {
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
        return car;
    }

    @Override
    public Car addCar(Car car) {
        Car carPersist;
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.openTransaction();
            car.setCategory(sessionFactory.getCurrentSession().getReference(car.getCategory()));
            carPersist = repository.save(car);
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
    public Car update(Car car, UUID id) {
        Transaction transaction = null;
        Car carUpdated;
        try {
            transaction = HibernateUtil.openTransaction();

            car.setId(id);
            carUpdated = repository.update(car);
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
    public List<Car> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice) {
        Transaction transaction = null;

        List<Car> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = repository.findCarsByFilters(brand, category, year, minPrice, maxPrice);
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
    public List<Car> findCarsWithSort(boolean isASC) {
        Transaction transaction = null;

        List<Car> cars;
        try {
            transaction = HibernateUtil.openTransaction();
            cars = repository.findCarWithSort(isASC);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }


        return cars;
    }

    public void assignCarToShowroom(Car car, CarShowroom showroom) {
        Transaction transaction = null;

        try {
            transaction = HibernateUtil.openTransaction();

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
