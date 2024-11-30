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

import java.util.Properties;

public class TestHelper {

    private static Properties getProperties(String url) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", url);
        return properties;
    }
    private static SessionFactory sessionFactory;

    public static SessionFactory configSessionFactory(String urlDB) {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Car.class)
                    .addAnnotatedClass(CarShowroom.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Review.class)
                    .addAnnotatedClass(Client.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
            sessionFactory = configuration.addProperties(getProperties(urlDB)).buildSessionFactory(serviceRegistry);
            System.out.println("Create TestSessionFact");
        }
        return sessionFactory;
    }
}
