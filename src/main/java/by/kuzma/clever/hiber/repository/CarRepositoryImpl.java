package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CarRepositoryImpl implements CarRepository {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();


    @Override
    public Car save(Car car) {
        sessionFactory.getCurrentSession().merge(car);
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
        CriteriaQuery<Car> selectQuery = sessionFactory.getCurrentSession().getCriteriaBuilder().createQuery(Car.class);
        selectQuery.from(Car.class).fetch("category", JoinType.INNER);

        return sessionFactory.getCurrentSession().createQuery(selectQuery).getResultList();
    }

    @Override
    public void deleteById(UUID id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.remove(currentSession.get(Car.class, id));
    }
}
