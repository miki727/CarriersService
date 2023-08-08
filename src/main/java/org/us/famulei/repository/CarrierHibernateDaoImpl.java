package org.us.famulei.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.us.famulei.model.Carrier;
import org.us.famulei.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CarrierHibernateDaoImpl implements ICarrierDao {
    private static final Logger logger = LoggerFactory.getLogger(CarrierHibernateDaoImpl.class);
    @Override
    public void save(Carrier carrier) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(carrier);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                logger.error("Save transaction failed, Rollback");
                transaction.rollback();
            }
            logger.error("Unable to save department or unable to close session", e);
        }


    }

    @Override
    public List<Carrier> getCarriers() {
        logger.info("Start to getCarriers from Postgres via Hibernate.");

        List<Carrier> carriers = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            Session session = sessionFactory.openSession();

            String hql = "from Carrier ";
            Query<Carrier> query = session.createQuery(hql);

            carriers = query.list();

            session.close();
        } catch (HibernateException e) {
            logger.error("Open session exception or close session exception", e);
        }

        logger.info("Get carriers {}", carriers);
        return carriers;
    }

    @Override
    public Carrier getById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Carrier carrier) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(carrier);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                logger.error("Delete transaction failed, Rollback");
                transaction.rollback();
            }
            logger.error("Unable to delete carrier or unable to close session", e);
        }
        return true;
    }

    @Override
    public Carrier getCarrierEagerBy(Long id) {
        String hql = "FROM Carrier c LEFT JOIN FETCH c.clients where c.id = :Id"; //LEFT JOIN FETCH: HQL里面的left join
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Carrier> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Carrier result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failed to retrieve data record", e);
            session.close();
            return null;
        }
    }
}
