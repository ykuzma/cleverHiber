package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Review;
import by.kuzma.clever.hiber.repository.ReviewRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import java.util.List;
import java.util.UUID;

public class ReviewServiceImpl implements ReviewService {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();
    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Review> findAll() {
        Transaction transaction = null;

        List<Review> reviews;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            reviews = repository.findAll();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return reviews;
    }

    @Override
    public Review findById(UUID id) {
        Transaction transaction = null;
        Review review;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            review = repository.findById(id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return review;
    }

    @Override
    public Review addReview(Review review) {
        Review reviewPersist;
        Transaction transaction = null;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            reviewPersist = repository.save(review);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return reviewPersist;
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
    public Review update(Review client, UUID id) {
        Transaction transaction = null;
        Review reviewUpdated;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            client.setId(id);
            reviewUpdated = repository.update(client);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return reviewUpdated;
    }

    @Override
    public List<Review> fullTextSearch(String predicate) {
        sessionFactory.getCurrentSession().beginTransaction();
        SearchSession searchSession = Search.session(sessionFactory.getCurrentSession());

        List<Review> result = searchSession.search(Review.class)
                .where(f -> f.match()
                        .fields("content")
                        .matching(predicate))
                .fetchAllHits();


        sessionFactory.getCurrentSession().getTransaction().commit();
        return result;
    }
}
