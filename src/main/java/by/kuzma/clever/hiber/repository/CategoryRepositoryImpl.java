package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();

    @Override
    public Category save(Category category) {
        sessionFactory.getCurrentSession().persist(category);
        return category;
    }

    @Override
    public List<Category> findAll() {

        return sessionFactory.getCurrentSession().createQuery("FROM Category", Category.class).list();
    }

    @Override
    public Category findById(UUID id) {

        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public void deleteById(UUID id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Category category = currentSession.get(Category.class, id);
        currentSession.remove(category);
    }

    @Override
    public Category update(Category category) {
        return sessionFactory.getCurrentSession().merge(category);
    }
}
