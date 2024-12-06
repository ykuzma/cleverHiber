package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Review;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class ReviewRepositoryImpl implements ReviewRepository {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();

    @Override
    public Review save(Review review) {
        sessionFactory.getCurrentSession().persist(review);
        return review;
    }

    @Override
    public List<Review> findAll() {
        String hql = "FROM Review r JOIN FETCH r.car join fetch r.client";
        Query<Review> query = sessionFactory.getCurrentSession().createQuery(hql, Review.class);

        return query.getResultList();
    }

    @Override
    public Review findById(UUID id) {
        return sessionFactory.getCurrentSession().get(Review.class, id);
    }

    @Override
    public void deleteById(UUID id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.remove(currentSession.get(Review.class, id));
    }

    @Override
    public Review update(Review review) {
        return sessionFactory.getCurrentSession().merge(review);
    }
}
