package by.kuzma.clever.hiber;

import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.entity.Category;
import by.kuzma.clever.hiber.entity.Client;
import by.kuzma.clever.hiber.entity.Review;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory configSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Car.class)
                    .addAnnotatedClass(CarShowroom.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Review.class)
                    .addAnnotatedClass(Client.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Create sessionFact");
        }
        return sessionFactory;
    }
}
