package org.us.famulei.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CarrierJDBCDaoImplTest {
    CarrierJDBCDaoImpl carrierDao;

    @Before
    public void setup() {
        carrierDao = new CarrierJDBCDaoImpl();
    }

    @After
    public void teardown() {
        carrierDao = null;
    }


    @Test
    public void getCarriersTest() {

        assertEquals(0, carrierDao.getCarriers().size());
    }


//    @Test
//    public void createCarriersTest() {
//
//        assertEquals(0, carrierDao.getCarriers().size());
//
//    }
//
//    @Test
//    public void updateCarriersTest() {
//
//        assertEquals(0, carrierDao.getCarriers().size());
//
//    }
//
//    @Test
//    public void deleteCarriersTest() {
//
//        assertEquals(0, carrierDao.getCarriers().size());
//
//    }
}
