package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {

    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();

    @Override
    public Client save(Client client) {
        sessionFactory.getCurrentSession().persist(client);
        return client;
    }

    @Override
    public List<Client> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Client", Client.class).list();
    }

    @Override
    public Client findById(UUID id) {
        return sessionFactory.getCurrentSession().get(Client.class, id);
    }

    @Override
    public void deleteById(UUID id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.remove(currentSession.get(Client.class, id));
    }

    @Override
    public Client update(Client client) {
        return sessionFactory.getCurrentSession().merge(client);
    }
}
