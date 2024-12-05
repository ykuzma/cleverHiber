package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.repository.CarShowroomRepository;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class CarShowroomServiceImpl implements CarShowroomService {

    private final CarShowroomRepository repository;

    public CarShowroomServiceImpl(CarShowroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CarShowroom> findAll() {
        Transaction transaction = null;

        List<CarShowroom> showrooms;
        try {
            transaction = HibernateUtil.openTransaction();
            showrooms = repository.findAll();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return showrooms;
    }

    @Override
    public List<CarShowroom> getShowroomWithAllCars() {
        Transaction transaction = null;

        List<CarShowroom> showrooms;
        try {
            transaction = HibernateUtil.openTransaction();
            showrooms = repository.getShowroomWithAllCars();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return showrooms;
    }

    @Override
    public CarShowroom findById(UUID id) {
        Transaction transaction = null;
        CarShowroom carShowroom;
        try {
            transaction = HibernateUtil.openTransaction();
            carShowroom = repository.findById(id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return carShowroom;
    }

    @Override
    public CarShowroom addShowroom(CarShowroom carShowroom) {
        CarShowroom showroomPersist;
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.openTransaction();
            showroomPersist = repository.save(carShowroom);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return showroomPersist;
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
    public CarShowroom update(CarShowroom carShowroom, UUID id) {
        Transaction transaction = null;
        CarShowroom showroomUpdated;
        try {
            transaction = HibernateUtil.openTransaction();
            carShowroom.setId(id);
            showroomUpdated = repository.update(carShowroom);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return showroomUpdated;
    }

}
