package by.kuzma.clever.hiber.repository.dao;

import by.kuzma.clever.hiber.HibernateUtil;
import by.kuzma.clever.hiber.entity.CarShowroom;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;
import java.util.UUID;

public class CarShowroomRepositoryImpl {

  /*  private final SessionFactory sessionFactory = HibernateUtil.configSessionFactory();

    @Override
    public CarShowroom save(CarShowroom carShowroom) {
        sessionFactory.getCurrentSession().persist(carShowroom);
        return carShowroom;
    }

    @Override
    public List<CarShowroom> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM CarShowroom", CarShowroom.class).list();
    }

    @Override
    public CarShowroom findById(UUID id) {
        return sessionFactory.getCurrentSession().get(CarShowroom.class, id);
    }

    @Override
    public void deleteById(UUID id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.remove(currentSession.get(CarShowroom.class, id));
    }

    @Override
    public CarShowroom update(CarShowroom carShowroom) {
        return sessionFactory.getCurrentSession().merge(carShowroom);
    }

    @Override
    public List<CarShowroom> getShowroomWithAllCars() {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaQuery<CarShowroom> query = currentSession.getCriteriaBuilder().createQuery(CarShowroom.class);
        Root<CarShowroom> root = query.from(CarShowroom.class);
        query.select(root);

        RootGraph<?> entityGraph = currentSession.getEntityGraph("CarShowroom.withCarAndCategory");

        return currentSession
                .createQuery(query)
                .setHint("jakarta.persistence.fetchgraph", entityGraph).getResultList();
    }*/
}
