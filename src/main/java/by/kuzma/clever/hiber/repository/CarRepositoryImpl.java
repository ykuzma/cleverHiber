package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Car;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarDao {

    private final Session entityManager;


    @Override
    public Car save(Car car) {

        return entityManager.merge(car);
    }

    @Override
    public Car update(Car car) {
        return entityManager.merge(car);
    }

    @Override
    public Car findById(UUID id) {
        return entityManager.get(Car.class, id);
    }

    @Override
     public List<Car> findAll() {
        CriteriaQuery<Car> selectQuery = entityManager.getCriteriaBuilder().createQuery(Car.class);
        selectQuery.from(Car.class).fetch("category", JoinType.INNER);

        return entityManager.createQuery(selectQuery).getResultList();
    }

    @Override
    public void deleteById(UUID id) {
        Session currentSession = entityManager;
        currentSession.remove(currentSession.get(Car.class, id));
    }

    @Override
    public List<Car> findAllWithPagination(int offset, int limit) {
        Session currentSession = entityManager;
        CriteriaQuery<Car> selectQuery = currentSession.getCriteriaBuilder().createQuery(Car.class);
        selectQuery.from(Car.class).fetch("category", JoinType.INNER);
        Query<Car> query = currentSession.createQuery(selectQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();

    }

    @Override
    public List<Car> findCarWithSort(boolean isASC) {
        Session currentSession = entityManager;
        Order<? super Car> order;
        if (isASC) {
            order = Order.asc(Car.class, "price");
        } else {
            order = Order.desc(Car.class, "price");
        }

        CriteriaQuery<Car> selectQuery = currentSession.getCriteriaBuilder().createQuery(Car.class);
        selectQuery.from(Car.class).fetch("category", JoinType.INNER);
        Query<Car> query = currentSession.createQuery(selectQuery);
        return query.setOrder(Collections.singletonList(order)).getResultList();

    }

    @Override
    public List<Car> findCarsByFilters(String brand, String category, int year, double minPrice, double maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Car> cq2 = cb.createQuery(Car.class);
        Root<Car> root2 = cq2.from(Car.class);
        root2.fetch("category", JoinType.INNER);

        Predicate[] predicate = createPredicate(brand, category, year, minPrice, maxPrice, cb, root2);

        cq2.select(root2).where(predicate);

        Query<Car> query2 = entityManager.createQuery(cq2);
        return query2.getResultList();
    }

    private Predicate[] createPredicate(String brand, String category, int year, double minPrice, double maxPrice, CriteriaBuilder cb, Root<Car> root2) {
        List<Predicate> predicates = new ArrayList<>();
        if (brand != null) {
            predicates.add(cb.equal(root2.get("mark"), brand));
        }
        if (category != null) {
            predicates.add(cb.equal(root2.get("category").get("name"), category));
        }
        if (year > 0) {
            predicates.add(cb.equal(root2.get("yearOfProduction"), year));
        }
        if (minPrice > 0) {
            predicates.add(cb.greaterThanOrEqualTo(root2.get("price"), minPrice));
        }
        if (maxPrice > 0) {
            predicates.add(cb.lessThanOrEqualTo(root2.get("price"), maxPrice));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
