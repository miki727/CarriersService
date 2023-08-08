package org.us.famulei.repository;

import org.us.famulei.model.Carrier;

import java.util.List;

public interface ICarrierDao {

    void save(Carrier carrier);
    List<Carrier> getCarriers();


    Carrier getById(Long id);


    boolean delete(Carrier carrier);

    Carrier getCarrierEagerBy(Long id);

}
