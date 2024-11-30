package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
