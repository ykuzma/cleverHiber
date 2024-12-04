package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.repository.CarRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Order;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
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
    public List<Car> findAllWithPagination(int pageNumber, int pageSize) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();

        CriteriaQuery<Car> selectQuery = currentSession.getCriteriaBuilder().createQuery(Car.class);
        selectQuery.from(Car.class);
        Query<Car> query = currentSession.createQuery(selectQuery);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        List<Car> resultList = query.getResultList();
        currentSession.getTransaction().commit();
        return resultList;

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

    @Override
    public List<Car> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice) {
        sessionFactory.getCurrentSession().beginTransaction();
        CriteriaBuilder cb =sessionFactory.getCurrentSession().getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();

        CriteriaQuery<Car> cq2 = cb.createQuery(Car.class);
        Root<Car> root2 = cq2.from(Car.class);
        root2.fetch("category", JoinType.INNER);
        if(brand != null) {
            predicates.add(cb.equal(root2.get("mark"), brand));
        }
        if(category != null) {
            predicates.add(cb.equal(root2.get("category").get("name"), category));
        }
        if(year > 0) {
            predicates.add(cb.equal(root2.get("yearOfProduction"), year));
        }
        if(minPrice > 0 ) {
            predicates.add(cb.greaterThanOrEqualTo(root2.get("price"), minPrice));
        }
        if(maxPrice > 0 ) {
            predicates.add(cb.lessThanOrEqualTo(root2.get("price"), maxPrice));
        }
        cq2.select(root2).where(predicates.toArray(new Predicate[0]));

        Query<Car> query2 = sessionFactory.getCurrentSession().createQuery(cq2);
        List<Car> resultList = query2.getResultList();
        sessionFactory.getCurrentSession().getTransaction().commit();
        return resultList;

    }

    @Override
    public List<Car> findCarsWithSort(boolean isASC) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Order<? super Car> order;
        if(isASC) {
            order = Order.asc(Car.class, "price");
        }else {
            order = Order.desc(Car.class, "price");
        }

        CriteriaQuery<Car> selectQuery = currentSession.getCriteriaBuilder().createQuery(Car.class);
        selectQuery.from(Car.class).fetch("category", JoinType.INNER);
        Query<Car> query = currentSession.createQuery(selectQuery);
        List<Car> resultList = query.setOrder(Collections.singletonList(order)).getResultList();
        currentSession.getTransaction().commit();
        return resultList;
    }


}
