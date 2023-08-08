package org.us.famulei.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.us.famulei.model.Client;
import org.us.famulei.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ClientHibernateDaoImpl implements IClientDao{
    private static final Logger logger = LoggerFactory.getLogger(ClientHibernateDaoImpl.class);
    @Override
    public void save(Client client) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error("failed to insert record", e);
            session.close();
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> result = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM Client";
        try {
            Query<Client> query = s.createQuery(hql);
            result = query.list();
            s.close();
        } catch (HibernateException e) {
            logger.error("Session close exception try again", e);
            s.close();
        }
        return result;
    }

    @Override
    public Client getById(Long id) {
        return null;
    }

    @Override
    public void delete(Client client) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            logger.error("Unable to delete client or unable to close session", e);
        }
    }

}
