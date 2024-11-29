package by.kuzma.clever.hiber;

import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.configSessionFactory();

        System.out.println(sessionFactory.isClosed());

        sessionFactory.close();

        System.out.println(sessionFactory.isClosed());
    }

}
