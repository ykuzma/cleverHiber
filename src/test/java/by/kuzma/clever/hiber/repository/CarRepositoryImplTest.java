package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.TestHelper;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class CarRepositoryImplTest {

    SessionFactory sessionFactory;

    @Container
    private static PostgreSQLContainer <?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    public CarRepositoryImplTest() {
       sessionFactory = TestHelper.configSessionFactory(postgres.getJdbcUrl());
    }

    @Test
    void save() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Car car = new Car();
        Category category = new Category();
        car.setCategory(category);
        session.persist(car);
        System.out.println(car);
        session.getTransaction().commit();
        session.close();

        System.out.println(car);
    }
}