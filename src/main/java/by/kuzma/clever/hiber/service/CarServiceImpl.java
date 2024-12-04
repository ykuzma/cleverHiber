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
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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
    public Car findById(UUID id) {
        Transaction transaction = null;
        Car car;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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


}
