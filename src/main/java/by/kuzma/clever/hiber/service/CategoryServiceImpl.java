package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Category;
import by.kuzma.clever.hiber.repository.CategoryRepository;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class CategoryServiceImpl implements CategoryService {

     private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        repository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        Transaction transaction = null;

        List<Category> categories;
        try {
            transaction = HibernateUtil.openTransaction();
            categories = repository.findAll();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category findById(UUID id) {
        Transaction transaction = null;
        Category category;
        try {
            transaction = HibernateUtil.openTransaction();
            category = repository.findById(id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public Category addCategory(Category category) {
        Category categoryPersist;
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.openTransaction();
            categoryPersist = repository.save(category);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return categoryPersist;
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
    public Category update(Category category, UUID id) {
        Transaction transaction = null;
        Category categoryUpdated;
        try {
            transaction = HibernateUtil.openTransaction();
            category.setId(id);
            categoryUpdated = repository.update(category);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return categoryUpdated;
    }
}
