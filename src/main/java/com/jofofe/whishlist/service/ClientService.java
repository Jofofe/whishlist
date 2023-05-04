package com.jofofe.whishlist.service;

import com.jofofe.whishlist.entity.Client;
import com.jofofe.whishlist.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ClientService extends AbstractService<Client, String, ClientRepository> {


    ClientService(ClientRepository repository) {
        super(repository);
    }

    public Optional<Client> findClientById(String id) {
        return repository.findById(id);
    }

}
