package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class CarRepositoryImpl implements CarRepository {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();


    @Override
    public Car save(Car car) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(car);
        session.getTransaction().commit();
        session.close();
        return car;
    }

    @Override
    public Car findById(UUID id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public void deleteByID(UUID id) {

    }
}
