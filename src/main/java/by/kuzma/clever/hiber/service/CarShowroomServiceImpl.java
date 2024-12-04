package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.repository.CarShowroomRepository;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;
import java.util.UUID;

public class CarShowroomServiceImpl implements CarShowroomService {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();
    private final CarShowroomRepository repository;

    public CarShowroomServiceImpl(CarShowroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CarShowroom> findAll() {
        Transaction transaction = null;

        List<CarShowroom> showrooms;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            HibernateCriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<CarShowroom> query = cb.createQuery(CarShowroom.class);
            Root<CarShowroom> root = query.from(CarShowroom.class);
            query.select(root);

            RootGraph<?> entityGraph = sessionFactory.getCurrentSession()
                    .getEntityGraph("CarShowroom.withCarAndCategory");

            showrooms = sessionFactory.getCurrentSession()
                    .createQuery(query)
                    .setHint("jakarta.persistence.fetchgraph", entityGraph).getResultList();
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
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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
    public CarShowroom update(CarShowroom carShowroom, UUID id) {
        Transaction transaction = null;
        CarShowroom showroomUpdated;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
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


    @Override
    public void assignCarToShowroom(Car car, CarShowroom showroom) {
        Transaction transaction = null;

        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            CarShowroom byId = repository.findById(showroom.getId());
            byId.addCar(car);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }

    }
}
