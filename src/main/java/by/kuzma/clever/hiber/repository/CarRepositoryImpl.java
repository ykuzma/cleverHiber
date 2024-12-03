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
        sessionFactory.getCurrentSession().persist(car);
        return car;
    }

    @Override
    public Car update(Car car) {
        return sessionFactory.getCurrentSession().merge(car);
    }

    @Override
    public Car findById(UUID id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }

    @Override
    public List<Car> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Car", Car.class).list();
    }

    @Override
    public void deleteByID(UUID id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.remove(currentSession.get(Car.class, id));
    }
}
