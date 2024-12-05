package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.Car;
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
            transaction = HibernateUtil.openTransaction();
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
            transaction = HibernateUtil.openTransaction();
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
            transaction = HibernateUtil.openTransaction();
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
    public Client update(Client client, UUID id) {
        Transaction transaction = null;
        Client clientUpdated;
        try {
            transaction = HibernateUtil.openTransaction();
            client.setId(id);
            clientUpdated = repository.update(client);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return clientUpdated;
    }

    @Override
    public void buyCar(Client client, Car car) {
        Transaction transaction = null;

        try {
            transaction = HibernateUtil.openTransaction();

            Client byId = repository.findById(client.getId());
            byId.addCar(sessionFactory.getCurrentSession().getReference(car));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
