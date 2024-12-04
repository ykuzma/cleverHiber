package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.entity.Client;
import by.kuzma.clever.hiber.repository.ClientRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> findAll() {
        Transaction transaction = null;

        List<Client> clients;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            clients = repository.findAll();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Client findById(UUID id) {
        Transaction transaction = null;
        Client client;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            client = repository.findById(id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public Client addClient(Client client) {
        Client clientPersist;
        Transaction transaction = null;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            clientPersist = repository.save(client);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return clientPersist;
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
    public Client update(Client client, UUID id) {
        Transaction transaction = null;
        Client ClientUpdated;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            client.setId(id);
            ClientUpdated = repository.update(client);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return ClientUpdated;
    }

    @Override
    public void buyCar(Client client, Car car) {
        Transaction transaction = null;

        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            Client byId = repository.findById(client.getId());
            byId.addCar(car);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
