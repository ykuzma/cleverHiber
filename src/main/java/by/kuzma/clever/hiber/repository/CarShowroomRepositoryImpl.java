package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.CarShowroom;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class CarShowroomRepositoryImpl implements CarShowroomRepository {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();

    @Override
    public CarShowroom save(CarShowroom carShowroom) {
        sessionFactory.getCurrentSession().persist(carShowroom);
        return carShowroom;
    }

    @Override
    public List<CarShowroom> findAll() {
        return null;
    }

    @Override
    public CarShowroom findById(UUID id) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public CarShowroom update(CarShowroom carShowroom) {
        return null;
    }
}
