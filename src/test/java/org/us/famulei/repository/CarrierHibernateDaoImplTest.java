package org.us.famulei.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.us.famulei.model.Carrier;
import org.us.famulei.model.Client;

import static org.junit.Assert.*;

public class CarrierHibernateDaoImplTest{
    private CarrierHibernateDaoImpl carrierHibernateDao;
    private IClientDao clientHibernateDao;
    private Carrier c1;
    private Client client1;
    private Client client2;

    @Before
    public void setUp() {
        carrierHibernateDao = new CarrierHibernateDaoImpl();
        c1 = new Carrier();
//        c1.setId((long) (Math.random()*(100L - 1L)));
        c1.setName("AT&T");
        c1.setDescription("random description");
        c1.setLocation("New York");
        carrierHibernateDao.save(c1);

        clientHibernateDao = new ClientHibernateDaoImpl();
        client1 = new Client();
        client1.setId((long) (Math.random()*(100L - 1L)));
        client1.setName("Aaron");
        client1.setAddress("Washington DC");
        client1.setCarrier(c1);
        clientHibernateDao.save(client1);

        client2 = new Client();
        client2.setId((long) (Math.random()*(100L - 1L)));
        client2.setName("Brian");
        client2.setAddress("Arlington VA");
        client2.setCarrier(c1);
        clientHibernateDao.save(client2);
    }

    @After
    public void tearDown() {
        clientHibernateDao.delete(client1);
        clientHibernateDao.delete(client2);
        carrierHibernateDao.delete(c1);
    }

    @Test
    public void getCarriersTest() {
        assertEquals(1, carrierHibernateDao.getCarriers().size());
    }

    @Test
    public void getCarrierEagerByTest() {
        Carrier carrier = carrierHibernateDao.getCarrierEagerBy(c1.getId());
        assertNotNull(carrier);
        assertEquals(carrier.getName(), c1.getName());
        assertTrue(carrier.getClients().size() > 0);
    }

}