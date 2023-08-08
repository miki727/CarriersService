package org.us.famulei.repository;

import org.us.famulei.model.Carrier;
import org.us.famulei.model.Client;

import java.util.List;

public interface IClientDao {

    void save(Client client);

    List<Client> getClients();

    Client getById(Long id);

    void delete(Client client);
}
